<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
  <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/layer/layer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 网站信息</strong></div>
  <div class="body-content">
    <form id="upload_form" method="post" class="form-x" enctype="multipart/form-data">
      <div class="form-group">
        <div class="label">
          <label>网站标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="stitle" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>网站LOGO：</label>
        </div>
        <div class="field">
          <%--隐藏 添加onchange属性进行处理， 再上传按钮上面重添加监听事件--%>
          <input type="file" id="upload_pic" name="upload_pic" onchange="imgPreview(this)" hidden="hidden"/><%-- 隐藏 --%>
          <input type="text" id="url1" name="slogo" class="input tips" style="width:45%; float:left;" value="" data-toggle="hover" data-place="right" data-image=""  />
          <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传" ><br><%-- 代替type=file 的input --%>
          <img src="" id="preview"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>网站域名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="surl" value="" />
        </div>
      </div>
      <div class="form-group" style="display:none">
        <div class="label">
          <label>副加标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sentitle" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>网站关键字：</label>
        </div>
        <div class="field">
          <textarea class="input" name="skeywords" style="height:80px"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>网站描述：</label>
        </div>
        <div class="field">
          <textarea class="input" name="sdescription"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>联系人：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_name" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>手机：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_phone" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_tel" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" style="display:none;">
        <div class="label">
          <label>400电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_400" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>传真：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_fax" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_qq" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" style="display:none">
        <div class="label">
          <label>QQ群：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_qqu" value="" />
          <div class="tips"></div>
        </div>
      </div>
     
      <div class="form-group">
        <div class="label">
          <label>Email：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_email" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>地址：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="s_address" value="" />
          <div class="tips"></div>
        </div>
      </div>  
              
      <div class="form-group">
        <div class="label">
          <label>底部信息：</label>
        </div>
        <div class="field">
          <textarea name="scopyright" class="input" style="height:120px;"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button id="sub_upload" class="button bg-main icon-check-square-o" type="button"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
  $("#image1").click(function () {
      $("#upload_pic").click(); //添加上传文件事件
  });

  function imgPreview(fileList) {
      //判断是否支持FileReader
      if (window.FileReader) {
          var reader = new FileReader();
      } else {
          alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
      }

      var file = fileList.files[0];
      var imageType = /^image\//;
      //alert(file.name);
      $("#url1").val(file.name);

      if(!imageType.test(file.type)) {
          alert("请选择图片！");
          return;
      }
      
      reader.onload = function (e) {
//          //获取图片dom
//          var img = document.getElementById("preview");
//          //图片路径设置为读取的图片
//          img.src = e.target.result;
          $("#preview").attr("src", e.target.result);
      }
      reader.readAsDataURL(file);
  }

  $("#sub_upload").click(function () {
      var formData = new FormData($( "#upload_form" )[0]);
      $.ajax({
          type: "POST",
          url: "http://localhost:8080/destiny/upload/up_pic",
          data: formData,
          dataType: "json", //返回数据data的类型
          contentType: false, //则不会为jqXHR设置请求头Content-Type
          processData: false, //代表jQuery不会将穿到服务器的data解析为字符串
          success : function (data) {
              if(data.success) {
                  alert(data.message);
                  layer.msg(data.message);
              } else {
                  layer.msg(data.message);
              }
          },
          error : function () {
              alert("未知错误");
          }
      });
  });

</script>
</body></html>