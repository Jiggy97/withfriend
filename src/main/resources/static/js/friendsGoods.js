var IMP = window.IMP;
IMP.init("imp18734116")
var buyer = "[[${buyer}]]";

function payRequest(button) {
// 클릭한 버튼에서 data-goods-name 및 data-goods-price 값을 가져옵니다.
var goodsName = button.getAttribute('data-goods-name');
var goodsPrice = button.getAttribute('data-goods-price');
var goodsStock = button.getAttribute('data-goods-stock');

if (goodsStock <= 0) {
    alert('해당 상품은 재고 부족으로 구매할 수 없습니다.');
} else {
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
        name: goodsName,
        amount: goodsPrice,
        buyer_name: buyer,
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
}