const form = elById('signup-form');

const firstName = elById('firstName');
const lastName = elById('lastName');
const birthday = elById('birthday');
const gender = elById('gender');
const email = elById('email');
const phone = elById('phone');
const avatar = elById('avatar');
const country = elById('country');
const city = elById('city');
const login = elById('login');
const password = elById('password');
const repeat = elById('repeatPassword');
const question = elById('question');
const answer = elById('answer');

const validators = new Array();
(()=>{
    validators.push(new InputValidator(
        firstName, elById('first-name-warn'), true, 1, 255, null, null)
    );
    validators.push(new InputValidator(
        lastName, elById('last-name-warn'), true, 1, 255, null, null)
    );
    validators.push(new InputValidator(
        birthday, elById('birthday-warn'), true, null, null, null, ()=>{
            const today = new Date().setHours(0, 0, 0, 0);
            const formDate = new Date(birthday.value).setHours(0, 0, 0, 0); 
            const error = elById('birthday-warn');

            if(formDate > today){
                error.textContent = 'Недопустимая дата';
                error.classList.add('visible');
                return false;
            }

            return true;   
        })
    );
    validators.push(new InputValidator(
        gender, elById('gender-warn'), true, null, null, null, null)
    );
    validators.push(new InputValidator(
        email, elById('e-mail-warn'), true, 5, 255, 'local-part@domain (напр. email@mail.ru)', null)
    );
    validators.push(new InputValidator(
        phone, elById('phone-warn'), true, 9, 17, '+xxx(yy)zzz-zz-zz или xxx-yy-xx', null)
    );
    validators.push(new InputValidator(
        avatar, elById('avatar-warn'), null, null, null, null, ()=>{
            const file = avatar.files[0];    
            if(file) {
                const error = elById('avatar-warn');
                if(file.type !== 'image/jpg' && file.type !== 'image/jpeg'){
                    error.textContent = 'Допустимые форматы файлов: jpg, jpeg';
                    error.classList.add('visible');
                    error.parentNode.classList.add('visible');
                    return false;
                }
                if((file.size / 1024 / 1024) > 5){
                    error.textContent = 'Максимальный размер файла 5МБ';
                    error.classList.add('visible');
                    error.parentNode.classList.add('visible');
                    return false;
                }

            }
            return true;
        })
    );
    validators.push(new InputValidator(
        country, elById('country-warn'), null, 1, 255, null, null)
    );
    validators.push(new InputValidator(
        city, elById('city-warn'), null, 1, 255, null, null)
    );
    validators.push(new InputValidator(
        login, elById('login-warn'), true, 1, 255, 'латинские буквы, цифры и символ "_"', null)
    );
    validators.push(new InputValidator(
        password, elById('password-warn'), true, 8, 255, 'латинские буквы, цифры и символ "_"', null)
    );
    validators.push(new InputValidator(
        repeat, elById('repeat-warn'), true, 8, 255, 'латинские буквы, цифры и символ "_"', ()=>{
            const error = elById('repeat-warn');

            if(password.value !== repeat.value){
                repeat.classList.add('invalid-input');
                error.textContent = "Пароли не совпадают";
                error.classList.add('visible');
                error.parentNode.classList.add('visible');
                return false;
            }
            
            return true;
        })
    );
    validators.push(new InputValidator(
        question, elById('question-warn'), true, null, null, null, null)
    );
    validators.push(new InputValidator(
        answer, elById('answer-warn'), true, 1, 255, null, null)
    );
})();

const avatarResetBtn = document.getElementById('avatar-reset-btn');

avatarResetBtn.onclick = (event) => {
    event.preventDefault();
    avatar.value = '';
    const avatarError = elById('avatar-warn');
    avatarError.classList.remove('visible');
    avatarError.textContent = '';
    avatarError.parentNode.classList.remove('visible');
};

function elById(id){
    return document.getElementById(id);
}

form.addEventListener('submit', (event)=>{
    validators.forEach((item)=>{
        if(!item.isValid()){
            event.preventDefault();
        }
    });
});