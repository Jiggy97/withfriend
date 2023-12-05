function updateGoods() {
    const formData = new FormData(document.querySelector('form'));

    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
    console.log(formDataObject);

    axios.patch('/goods/', formDataObject)
        .then(response => {
            console.log('Success:', response.data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
