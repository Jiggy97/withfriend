const requestPoint = (button) => {
    const chargePoint = prompt("충전할 금액을 입력해 주세요");
    location.href = "/payment/point/" + chargePoint;
}