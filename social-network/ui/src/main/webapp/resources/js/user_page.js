const addRecordButton = elById('add-record-btn');
const recordFormContentItem = elById('record-form-content-item');

addRecordButton.onclick = (event) => {
    recordFormContentItem.classList.toggle('active');
};

const recordForm = elById('record-form');
const recordHeader = elById('record-header');
const recordHeaderWarning = elById('record-header-warning');
const recordDescription = elById('record-description');
const recordDescriptionWarning = elById('record-description-warning');
const recordFile = elById('record-file-input');
const recordFileWarning = elById('record-file-warning');

const validators = new Array();
(()=>{
    validators.push(new InputValidator(
        recordHeader, recordHeaderWarning, true, 1, 255, null, null
    ));
    validators.push(new InputValidator(
        recordDescription, recordDescriptionWarning, null, null, null, null, ()=>{
            if(recordDescription.value === ''){
                recordDescriptionWarning.textContent = 'Пропущено обязательное поле!';
                recordDescriptionWarning.classList.add('visible');
                recordDescriptionWarning.parentNode.classList.add('visible');
                recordDescription.classList.add('invalid-input');
                return false;
            }
            return true;
        })
    );
    validators.push(new InputValidator(
        recordFile, recordFileWarning, null, null, null, null, ()=>{
            const file = recordFile.files[0]; 
             
            if(file) {
                if(file.type !== 'image/jpg' && file.type !== 'image/jpeg'){
                    recordFileWarning.textContent = 'Допустимые форматы файлов: jpg, jpeg';
                    recordFileWarning.classList.add('visible');
                    recordFileWarning.parentNode.classList.add('visible');
                    return false;
                }
                if((file.size / 1024 / 1024) > 5){
                    recordFileWarning.textContent = 'Максимальный размер файла 5МБ';
                    recordFileWarning.classList.add('visible');
                    recordDescriptionWarning.parentNode.classList.add('visible');
                    return false;
                }
            }
            return true;
        })
    );
})();

recordForm.addEventListener('submit', (event) => {
    validators.forEach((item)=>{
        if(!item.isValid()){
            console.log(item);
            event.preventDefault();
        }
    });
});


const recordFormResetBtn = elById('record-reset-btn');
recordFormResetBtn.onclick = (event) => {
    elById('record-form-content-item').classList.remove('active');
};

const recordFileCleanBtn = elById('record-file-clean-btn');
recordFileCleanBtn.onclick = (event) => {
    event.preventDefault();
    recordFile.value = '';
    recordFileWarning.parentNode.classList.remove('visible');
    recordFileWarning.classList.remove('visible');
    recordFileWarning.textContent = '';
};


function elById(id){
    return document.getElementById(id);
}