/**
 * 提取图片上传方法，方便合页面的时候迁移js
 * <strong>Copyright (C) zhl Co.,Ltd.</strong> <br>
 * </p>
 * @author zts zhaotisheng@qq.com <br>
 * @version <strong>partjob-0.1.0</strong> <br>
 * attention !!
 * @depend on ajaxFileUpload.js
 * <br>
 */
(function(root,factory,plugName){
	factory(root.jQuery,plugName);
})(window,function($,plugName){
	var __DEFAULT__ = {
			url : ""//
			,succ:{}
			,data:{}
			,err:{}
	};
	var __PROTOTYPE__={
			_init:function(){
				this.upload_(this,this.url);
			}
			,upload_:function(_this,url){
				if(!url){
					console.log("url不能为空");
					return;
				}
//				console.log(JSON.stringify("xxxxxxx:"+this.data));
				$.ajaxFileUpload
			    (
			        {
			        	url: url,//用于文件上传的服务器端请求地址
			            secureuri: false, 
			            fileElementId: $.trim(this.selector.replace('#','')), //文件上传域的ID
			           	dataType: 'json', //返回值类型 一般设置为json
			           	data:this.data,
			            success: function (data, status)  //服务器成功响应处理函数
			            {	
//			            	console.log("成功"+JSON.stringify(data));
			            	if(!data.success){
			            		alert("异常，注意上传格式");
			            		return;
			            	}
			            	_this.succ(data);
			            },
			            error: function (data, status, e)//服务器响应失败处理函数
			            {
//			                alert(e);
//			            	console.log("失败"+JSON.stringify(data));
			            	_this.err(e);
			            }
			        }
			    );
			}
			
	};
	$.fn[plugName]=function(options){
		$.extend(this,__DEFAULT__,options,__PROTOTYPE__);
		this._init();
	}
},"Imgupload");
