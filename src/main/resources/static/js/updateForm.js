function updateGoods() {
    // 폼 데이터 수집
    const formData = new FormData(document.querySelector('form'));

    // 폼 데이터를 객체로 변환
    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
    console.log(formDataObject);

    // Axios를 사용하여 API 호출
    axios.patch('/goods/', formDataObject)
        .then(response => {
            console.log('Success:', response.data);
            // 성공적인 응답 처리
        })
        .catch(error => {
            console.error('Error:', error);
            // 오류 처리
        });
}
