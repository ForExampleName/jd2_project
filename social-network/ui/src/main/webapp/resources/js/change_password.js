const form = document.getElementsByTagName('form')[0];
const password = document.getElementById('password');
const repeatPassword = document.getElementById('repeat-password');
const passwordError = document.getElementById('password-warn');
const repeatPasswordError = document.getElementById('repeat-warn');

const validators = new Array();
 (()=>{
    validators.push(new InputValidator(password, passwordError, true, 8, 255, null, null));
    validators.push(new InputValidator(repeatPassword, repeatPasswordError, true, 8, 255, null, ()=>{
        if(password.value !== repeatPassword.value){
            repeatPassword.classList.add('invalid-input');
            repeatPasswordError.textContent = 'Пароли не совпадают';
            repeatPasswordError.classList.add('visible');
            repeatPasswordError.parentNode.classList.add('visible');
            return false;
        }

        return true;
    }));
})();

form.addEventListener('submit', (event)=>{
    validators.forEach((item)=>{
        if(!item.isValid()){
            event.preventDefault();
        }
    });
});