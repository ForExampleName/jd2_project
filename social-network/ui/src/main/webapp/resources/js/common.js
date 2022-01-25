const sidebar = document.getElementById('sidebar');
const sidebarLightModeSwitch = document.getElementById('light-mode-switch-sidebar');
const mobileLightModeSwitch = document.getElementById('light-mode-switch-mobile');
const mobileMenuButton = document.getElementById('mobile-menu-button');
const accountDropdown = document.getElementById('account-dropdown');
const accountManagementItems = document.getElementsByClassName('management-item');

mobileMenuButton.onclick = (event)=>{
    sidebar.classList.toggle('active');
    for (let i = 0; i < accountManagementItems.length; i++) {
        accountManagementItems.item(i).classList.remove('active');
    }
};

window.matchMedia("(min-width: 1200px)").addListener((mediaQuery) => {
    if(mediaQuery.matches){
        sidebar.classList.remove('active');//colxoz
    }
});

sidebarLightModeSwitch.onchange = () => {
    document.body.classList.toggle('dark');
    mobileLightModeSwitch.checked = sidebarLightModeSwitch.checked;
};

mobileLightModeSwitch.onchange = () => {
    document.body.classList.toggle('dark');
    sidebarLightModeSwitch.checked = mobileLightModeSwitch.checked;
};

accountDropdown.onclick = (event) => {
    for (let i = 0; i < accountManagementItems.length; i++) {
        accountManagementItems.item(i).classList.toggle('active');
    }
};