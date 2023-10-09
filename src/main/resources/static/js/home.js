const requestPoint = (button) => {
    let chargePoint;

    while (true) {
        const userInput = prompt("충전할 금액을 입력해 주세요");

        if (userInput === null) {
            location.href = "/main/home";
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

    location.href = "/payment/point/" + chargePoint;
}
