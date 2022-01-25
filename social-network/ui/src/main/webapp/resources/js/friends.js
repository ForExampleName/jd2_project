const filterSwitch = elById('filter-switch');
const filterSwitchIcon = elById('filter-switch-icon');
const filterFormContainer = elById('filter-form-container');

filterSwitch.onclick = (event) => {
    filterSwitch.classList.toggle('active');
    filterSwitchIcon.classList.toggle('active');
    filterFormContainer.classList.toggle('active');
};

const filterForm = elById('filter-form');
const filterFormLeftAgeBorder = elById('age-left-border');
const filterFormRightAgeBorder = elById('age-right-border');
const filterFormAgeBorderError = elById('age-warn');

const validators = new Array();
(()=>{
    validators.push(new InputValidator(
        filterFormLeftAgeBorder, filterFormAgeBorderError, false, null, 3, null, () => {
            const leftBorderValue = filterFormLeftAgeBorder.value;

            if(leftBorderValue !== ''){
                if(leftBorderValue.match(/[a-zA-Z]/)){
                    filterFormLeftAgeBorder.classList.add('invalid-input');
                    return false;
                }  
            }

            return true;
        })
    );

    validators.push(new InputValidator(
        filterFormRightAgeBorder, filterFormAgeBorderError, false, null, 3, null, () => {
            const leftBorderValue = filterFormLeftAgeBorder.value;
            const rightBorderValue = filterFormRightAgeBorder.value;

            if(rightBorderValue !== ''){
                if(rightBorderValue.match(/[a-zA-Z]/)){
                    filterFormRightAgeBorder.classList.add('invalid-input');
                    return false;
                }  
                if(leftBorderValue !== '' && !leftBorderValue.match(/[a-zA-Z]/)){
                    if(Number.parseInt(leftBorderValue, 10) > Number.parseInt(rightBorderValue, 10)){
                        filterFormLeftAgeBorder.classList.add('invalid-input');
                        filterFormRightAgeBorder.classList.add('invalid-input');
                        filterFormAgeBorderError.textContent = 'Недопустимый диапазон';
                        filterFormAgeBorderError.classList.add('visible');
                        filterFormAgeBorderError.parentNode.classList.add('visible');
                        return false;
                    }
                }
            }

            return true;
        })
    );
})();

filterForm.addEventListener('submit', (event)=>{
    validators.forEach((item)=>{
        if(!item.isValid()){
            event.preventDefault();
        }
    });
});

const filterResetBtn = elById('filter-reset-btn');

filterResetBtn.onclick = (event) => {
    filterFormLeftAgeBorder.classList.remove('invalid-input');
    filterFormRightAgeBorder.classList.remove('invalid-input');
    filterFormAgeBorderError.textContent = '';
    filterFormAgeBorderError.classList.remove('visible');
    filterFormAgeBorderError.parentNode.classList.remove('visible');
};


function elById(id){
    return document.getElementById(id);
}