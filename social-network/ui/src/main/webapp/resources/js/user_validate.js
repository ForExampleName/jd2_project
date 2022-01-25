const form = document.getElementsByTagName('form')[0];
const input = document.getElementById('answer');

form.addEventListener('submit', (event) => {
    if(input.value === ''){
        event.preventDefault();
    }
});