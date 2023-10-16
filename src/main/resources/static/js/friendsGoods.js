var today = new Date();
var hours = today.getHours(); // 시
var minutes = today.getMinutes(); // 분
var seconds = today.getSeconds(); // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours + minutes + seconds + milliseconds;

function payPoint(button) {
    // 클릭한 버튼에서 data-goods-name 및 data-goods-price 값을 가져옵니다.
    var goodsId = button.getAttribute('data-goods-id');
    var goodsName = button.getAttribute('data-goods-name');
    var goodsPrice = button.getAttribute('data-goods-price');
    var goodsStock = button.getAttribute('data-goods-stock');
    var sellerUserId = button.getAttribute('data-seller-user-id');
    var buyerUserId = button.getAttribute('data-buyer-user-id');
    let purchaseQuan;

    while (true) {
            const userInput = prompt("구매할 개수를 입력해 주세요");

            if (userInput === null) {
                location.href = homeUrl;
                return; // 사용자가 취소 버튼을 누른 경우 함수 종료
            }

            purchaseQuan = parseInt(userInput);

            if (!isNaN(purchaseQuan)) {
                // 숫자로 변환 가능한 경우
                break; // 반복문 종료
            } else {
                alert("숫자를 입력해 주세요. (0 < 입력 가능 숫자 <= 남은 재고)"); // 숫자로 변환할 수 없는 경우 경고 메시지 출력
            }
        }

    if (goodsStock <= 0 || goodsStock < purchaseQuan) {
        alert('해당 상품은 재고 부족으로 구매할 수 없습니다.');
    } else {
        axios({
            url: "/user/point",
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                goodsId: goodsId,
                goodsName: goodsName,
                goodsPrice: goodsPrice,
                goodsStock: goodsStock,
                purchaseQuan: purchaseQuan,
                sellerUserId: sellerUserId,
                buyerUserId: buyerUserId,
                makeMerchantUid: makeMerchantUid,
            }
        }).then((data) => {
            console.log("유적 간 상품 거래 결제 성공");
        }).catch(error => {
            console.log("유적 간 상품 거래 결제 오류");
            console.error(error);
        });
    }
}