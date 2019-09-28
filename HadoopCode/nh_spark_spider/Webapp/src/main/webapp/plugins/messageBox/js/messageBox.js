var messageBox = {
    show: function() {
        document.getElementsByClassName('box-bg')[0].style.zIndex = '888';
        document.getElementsByClassName('box-bg')[0].style.display = 'block';
        document.getElementsByClassName('testConnect')[0].style.display = 'block';
    },
    message: function(message) {
        $("#alert-text").text(message);
        messageBox.show();
        messageBox.addBtnEvent();
    },
    hide: function() {
        document.getElementsByClassName('box-bg')[0].style.zIndex = '99';
        document.getElementsByClassName('box-bg')[0].style.display = 'none';
        document.getElementsByClassName('testConnect')[0].style.display = 'none';
    },
    addBtnEvent: function() {
        $(".testConnect-footer-btn").unbind("click");
        $(".testConnect-header-i").unbind("click");

        //MessageBox 关闭按钮绑定
        $(".testConnect-footer-btn").bind("click", function() {
            messageBox.hide();
        });

        $(".testConnect-header-i").bind("click", function() {
            messageBox.hide();
        });
    }
};

var confirmBox = {
    show: function() {
        document.getElementsByClassName('box-bg')[0].style.zIndex = '888';
        document.getElementsByClassName('box-bg')[0].style.display = 'block';
        document.getElementsByClassName('testConnect')[1].style.display = 'block';
    },
    confirm: function(message, callback) {
        $("#confirm-text").text(message);
        confirmBox.show();

        $("#confirm-positive").unbind();
        $("#confirm-positive").bind("click", function() {
            confirmBox.hide();
            callback(true);
        });
        $("#confirm-negative").unbind();
        $("#confirm-negative").bind("click", function() {
            confirmBox.hide();
            callback(false);
        });
    },
    hide: function() {
        document.getElementsByClassName('box-bg')[0].style.zIndex = '99';
        document.getElementsByClassName('box-bg')[0].style.display = 'none';
        document.getElementsByClassName('testConnect')[1].style.display = 'none';
    }
};
