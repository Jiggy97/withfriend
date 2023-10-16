var IMP = window.IMP;
IMP.init("imp18734116");
const homeUrl = "/main/home";

const requestPoint = (button) => {
    var userName = button.getAttribute('data-user-name');
    var userId = button.getAttribute('data-user-id');
    let chargePoint;

    while (true) {
        const userInput = prompt("충전할 금액을 입력해 주세요");

        if (userInput === null) {
            location.href = homeUrl;
            return; // 사용자가 취소 버튼을 누른 경우 함수 종료
        }

        chargePoint = parseInt(userInput);

        if (!isNaN(chargePoint)) {
            // 숫자로 변환 가능한 경우
            break; // 반복문 종료
        } else {
            alert("유효한 숫자를 입력하세요."); // 숫자로 변환할 수 없는 경우 경고 메시지 출력
        }
    }

    requestPay(chargePoint, userId, userName);
}

function requestPay(chargePoint, userId, userName) {
    var today = new Date();
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes(); // 분
    var seconds = today.getSeconds(); // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours + minutes + seconds + milliseconds;

    IMP.request_pay({
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: "IMP" + makeMerchantUid,
        name: "charge trust market point",
        amount: chargePoint,
        buyer_name: userName,
        m_redirect_url: homeUrl
    }, async function(rsp) { // callback
        //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
        console.log(rsp)
        if (rsp.success) {
            // 인증 토큰 발급 받기
            axios({
                url: "/portone/token?imp_uid=" +  rsp.imp_uid + "&charge_point=" + chargePoint,
                method: "get"
                }).then(response => {
                    console.log(response.data.success);
                    console.log(response.data.message);
                    axios({
                        url: "/payment/point",
                        method: "post",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        data: {
                            imp_uid: rsp.imp_uid,
                            merchant_uid: rsp.merchant_uid,
                            chargePoint: chargePoint,
                            userId: userId
                        }
                    }).then((data) => {
                        console.log("결제 내역, db 저장 api 요청 완료");
                    }).catch(error => {
                        console.log("결제 완료 시, db 저장 도중 오류");
                        console.error(error);
                    })
                }).catch(error => {
                    console.log("토큰 발급 실패 시");
                    console.error(error);
                });
            // portone에 결제 요청 성공 시
            // 팝업 알림을 띄우고 사용자가 확인 버튼을 누를 때 이벤트 처리
            if (window.confirm("결제에 성공하였습니다. 확인을 누르세요.")) {
                console.log("사용자가 확인을 눌렀습니다.");
                goToHome();
            } else {
                console.log("사용자가 취소를 눌렀습니다.");
                // 결제 취소 로직을 나중에 짜면 될듯
            }
        } else {
            // portone에 결제 요청 실패 시
            alert(`결제에 실패하였습니다.`);
        }
    });
}

function goToHome() {
    location.href = homeUrl;
}