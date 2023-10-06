// 모달 열기
function openModal() {
    var modal = document.getElementById('myModal');
    modal.style.display = 'block';
}

// 모달 닫기
function closeModal() {
    var modal = document.getElementById('myModal');
    modal.style.display = 'none';
}

// 모달 창 바깥을 클릭하면 모달 닫기
window.onclick = function(event) {
    var modal = document.getElementById('myModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}

// 포인트 충전 버튼 클릭 시 모달 열기
function payRequest(button) {
    openModal();
}
