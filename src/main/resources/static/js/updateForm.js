function updateGoods() {
    // 폼 데이터 수집
    const formData = {
        id: document.getElementById('id').value,
        name: document.getElementById('name').value,
        price: document.getElementById('price').value,
        description: document.getElementById('description').value,
        stock: document.getElementById('stock').value,
    };

    // PATCH 메서드로 API 호출
    fetch('/trust/goods/', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        // 성공적으로 처리된 경우 추가 로직을 여기에 작성
    })
    .catch(error => {
        console.error('Error:', error);
    });
}