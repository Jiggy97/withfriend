var IMP = window.IMP;
IMP.init("imp18734116");

function requestPay(chargePoint, userName) {
    var today = new Date();
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;

    IMP.request_pay({
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: "IMP" + makeMerchantUid,
        name: "charge trust market point",
        amount: chargePoint,
        buyer_name: userName,
        m_redirect_url: "localhost:8083/main/home"
    }, function (rsp) { // callback
        //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
        if (rsp.success) {
            console.log(rsp);
        } else {
            console.log(rsp);
        }
    });
}