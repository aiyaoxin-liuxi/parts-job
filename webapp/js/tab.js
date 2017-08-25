/**
 * Created by XY on 2017/3/23.
 */
//ʹ�������ѡ�����
$(function(){
    $("#gou").on("click",function(){
        if($(this).attr("src")=="image/check.png"){
            $(this).attr("src","image/checked.png")
        }else{
            $(this).attr("src","image/check.png")
        }
    })
})

/*********************��ർ��������ʾ��ѡ����ɫ���滻**********************/
$(function(){
    $(".dian11,.dian22").on("click",function(){
        if ($(this).hasClass("show")) {
            $(this).addClass("hide").removeClass("show").siblings().removeClass("hide").addClass("show");
        }
    })

    $(".dian1").on("click",function(){
        if ($(this).hasClass("fabu_checked")) {
            $(this).addClass("fabu_check").removeClass("fabu_checked").siblings().removeClass("fabu_check").addClass("fabu_checked");
        }
    })

    $(".onc11").on("click",function(){
        $(".dian11").toggle();
    })

    $(".onc22").on("click",function(){
        $(".dian22").toggle();
    })
})

/********************������ʾ*************************/
$(function(){
    $('#datetimepicker1').datetimepicker({
        datepicker:false,
        format:'H:i',
        step:5
    });
    $('#datetimepicker2').datetimepicker({
        datepicker:false,
        format:'H:i',
        step:5
    });
})



/*******************************������Ч��*****************************/

$(function(){
    $(".dianji").on("click",function(){
        if ($(this).hasClass("ched")) {
            $(this).addClass("che").removeClass("ched").siblings().removeClass("che").addClass("ched");
        }
    })

    $(".dianji,.guanbi").on("click",function(){
        $(".jz-tanchu,.shadow").addClass("hide1");
    })

    $(".fabu_jz").on("click", function () {
        $(".jz-tanchu_1,.shadow").removeClass("hide1");
    });
    
    $(".fabu_jz").on("click", function () {
        $(".jz-tanchu,.shadow").removeClass("hide1");
    });

    $(".active").on("click", function () {
        $(".shadow,.jz-tanchu3").removeClass("hide1");
    });
    $(".fabu_jz2").on("click", function () {
        $(".jz-tanchu4,.shadow").removeClass("hide1");
    });

})

///**************************��ҳ�ֲ�ͼ************************/
//var swiper = new Swiper('.swiper-container', {
//    pagination: '.swiper-pagination',
//    paginationClickable: '.swiper-pagination',
//    nextButton: '.swiper-button-next',
//    prevButton: '.swiper-button-prev',
////        spaceBetween: 30,
////        effect: 'fade',//�ֲ���Ч��
////        autoplay : 3000,
////        autoplayDisableOnInteraction : false,//���ֻ���������Զ�
////        //grabCursor : true,//����С��Ч��
////        speed:300
//    loop: true//��ͷ���Լ���
//});
//
///**************************�Ҽ�ְ�ֲ�ͼ************************/
//var swiper = new Swiper('.swiper-container', {
//    pagination: '.swiper-pagination',
//    paginationClickable: '.swiper-pagination',
//    nextButton: '.swiper-button-next',
//    prevButton: '.swiper-button-prev',
////        spaceBetween: 30,
////        effect: 'fade',//�ֲ���Ч��
//    autoplay: 3000,
////        autoplayDisableOnInteraction : false,//���ֻ���������Զ�
////        //grabCursor : true,//����С��Ч��
////        speed:300
//    loop: true//��ͷ���Լ���
//});


/**********************�ҵļ�ְҳ��ѡ��Ч��***********************/
$(function(){
    $(".onc_jz").on("click",function(){
        $(this).addClass("active_jz").siblings().removeClass("active_jz");
    })
});

$(document).ready(function(){
    $(".content1").show();
    $(".content2").hide();
    $(".content3").hide();
    $(".content4").hide();
    $("#a1").click(function(){
        $(".content").hide();
        $(".content1").show();
    });
    $("#a2").click(function(){
        $(".content").hide();
        $(".content2").show();
    });
    $("#a3").click(function(){
        $(".content").hide();
        $(".content3").show();
    });
    $("#a4").click(function(){
        $(".content").hide();
        $(".content4").show();
    });
});


/***********************��ҵ����**********************/
$(function(){
    $('#img').change(function() {
        var file = this.files[0];
        var r = new FileReader();
        r.readAsDataURL(file);
        $(r).load(function() {
            $('#photo').children("img").attr("src",this.result);
        })
    });
})

$(function(){
    $('#img1').change(function() {
        var file = this.files[0];
        var r = new FileReader();
        r.readAsDataURL(file);
        $(r).load(function() {
            $('#photo1').find("#child").attr("src",this.result);
        })
    });
})

/*******************************�˻���ֵ************************************/
$(function(){
    $(".onca").on("click",function(){
        if ($(this).hasClass("xuanze1")) {
            $(this).addClass("xuanze").removeClass("xuanze1").siblings().removeClass("xuanze").addClass("xuanze1");
        }
    })
})
