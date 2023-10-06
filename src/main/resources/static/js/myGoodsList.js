const detailAndUpdate = (button) => {
  const id = button.getAttribute('data-goods-id');
  location.href = "/trust/goods/" + id;
}