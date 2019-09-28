<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>messageBox</title>
</head>

<body>
    <!-- 遮罩 -->
    <div class="zzc box-bg"></div>
    <!-- messageBox -->
    <div class="testConnect">
        <div class="testConnect-header">
            <span>提示</span> <i class="testConnect-header-i">x</i>
        </div>
        <div class="testConnect-centent">
            <p id="alert-text">测试连接失败</p>
        </div>
        <div class="testConnect-footer">
            <span class="testConnect-footer-btn">确认</span>
        </div>
    </div>
    <!-- confirmBox -->
    <div id="confirmBox" class="testConnect">
        <div class="testConnect-header">
            <span>提示</span> <i class="testConnect-header-i" onclick="confirmBox.hide()">x</i>
        </div>
        <div class="testConnect-centent">
            <p id="confirm-text">是否确认此操作？</p>
        </div>
        <div class="testConnect-footer">
            <button class="confrim-btn" id="confirm-positive">确认</button>
            <button class="confrim-btn gray" id="confirm-negative">取消</button>
        </div>
    </div>
</body>

</html>
