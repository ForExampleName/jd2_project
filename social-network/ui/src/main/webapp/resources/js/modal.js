const notification = document.getElementById('notification-modal');
const notificationButton = document.getElementById('notification-btn');

// window.onclick = closeNotificationModal;
notificationButton.onclick = closeNotificationModal;

function closeNotificationModal(){
    notification.style.display = "none";
}