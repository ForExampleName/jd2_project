class InputValidator{
    constructor(inputItem, warningItem, required, lessThen, greaterThen, pattern, customValidation){
        this.inputItem = inputItem;
        this.warningItem = warningItem;
        this.required = required;
        this.lessThen = lessThen;
        this.greaterThen = greaterThen;
        this.pattern = pattern;
        this.customValidation = customValidation;
    }

    isValid(){
        console.log(this.inputItem);
        if(this.inputItem.validity.valid){
            if(this.customValidation !== null){
                if(!this.customValidation()){
                    return false;
                }
            }
            
            this.inputItem.classList.remove('invalid-input');
            this.warningItem.classList.remove('visible');
            this.warningItem.textContent = '';

            if(this.warningItem === this.warningItem.parentNode.firstElementChild){
                this.warningItem.parentNode.classList.remove('visible');
            }

            return true;
        }
        else{
            if(this.required !== null){
                if(this.inputItem.validity.valueMissing){
                    this.inputItem.classList.add('invalid-input');
                    this.warningItem.textContent = 'Пропущено обязательное поле!';
                    this.warningItem.classList.add('visible');
                    this.warningItem.parentNode.classList.add('visible');
                    return false;
                }
            }
            if(this.lessThen !== null){
                if(this.inputItem.validity.tooShort){
                    this.inputItem.classList.add('invalid-input');
                    this.warningItem.textContent = `Должно быть не менее ${this.lessThen} символов!`;
                    this.warningItem.classList.add('visible');
                    this.warningItem.parentNode.classList.add('visible');
                    return false;
                }
            }
            if(this.greaterThen !== null){
                if(this.inputItem.validity.tooLong){
                    this.inputItem.classList.add('invalid-input');
                    this.warningItem.textContent = `Должно быть менее ${this.greaterThen} символов!`;
                    this.warningItem.classList.add('visible');
                    this.warningItem.parentNode.classList.add('visible');
                    return false;
                }
            }
            if(this.pattern !== null){
                if(this.inputItem.validity.patternMismatch){
                    this.inputItem.classList.add('invalid-input');
                    this.warningItem.textContent = `Информация в формате: ${this.pattern}`;
                    this.warningItem.classList.add('visible');
                    this.warningItem.parentNode.classList.add('visible');
                    return false;
                }
            }
        } 
    }
}