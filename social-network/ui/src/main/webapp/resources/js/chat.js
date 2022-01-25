const messageForm = document.getElementById('message-form');
const message = document.getElementById('message');

messageForm.addEventListener('submit', (event) => {
    if(message.value === ''){
        event.preventDefault();
    }
});

const chatWindow = document.getElementById('chat');
chatWindow.scrollTop = chatWindow.scrollHeight;