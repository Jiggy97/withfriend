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
    var buyerId = button.getAttribute('data-buyer-id');

    if (goodsStock <= 0) {
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
                buyerId: buyerId,
                makeMerchantUid: makeMerchantUid
            }
        }).then((data) => {
            console.log("유적 간 상품 거래 결제 성공");
        }).catch(error => {
            console.log("유적 간 상품 거래 결제 오류");
            console.error(error);
        });
    }
}