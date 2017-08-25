<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" href="${basePath}/css/tab.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.SuperSlide.2.1.1.js"></script>
    <style>
        /* 本例子css */
        .multipleColumn {
            overflow: hidden;
            position: relative;
            width: 1200px;
            /*border: 1px solid #ccc;*/
        }

        .multipleColumn .hd {
            overflow: hidden;
            height: 30px;
            background: #f4f4f4;
            padding: 0 10px;
        }

        .multipleColumn .hd .prev, .multipleColumn .hd .next {
            display: block;
            width: 5px;
            height: 9px;
            float: right;
            margin-right: 5px;
            margin-top: 10px;
            overflow: hidden;
            cursor: pointer;
            background: url("${basePath}/images/icoLeft.gif") no-repeat;
        }

        .multipleColumn .hd .next {
            background: url("${basePath}/images/icoRight.gif") no-repeat;
        }

        .multipleColumn .hd ul {
            float: right;
            overflow: hidden;
            zoom: 1;
            margin-top: 10px;
            zoom: 1;
        }

        .multipleColumn .hd ul li {
            float: left;
            width: 9px;
            height: 9px;
            overflow: hidden;
            margin-right: 5px;
            text-indent: -999px;
            cursor: pointer;
            background: url("${basePath}/images/icoCircle.gif") 0 -9px no-repeat;
        }

        .multipleColumn .hd ul li.on {
            background-position: 0 0;
        }

        .multipleColumn .bd {
            padding: 10px;
            overflow: hidden;
        }

        .multipleColumn .bd ul {
            overflow: hidden;
            zoom: 1;
            width: 240px;
            float: left;
            _display: inline;
        }

        .multipleColumn .bd ul li {
            margin: 0 8px;
            float: left;
            _display: inline;
            overflow: hidden;
            text-align: center;
        }

        .multipleColumn .bd ul li .pic {
            text-align: center;
            padding: 20px;
        }

        .multipleColumn .bd ul li .pic img {
            width: 150px;
            height: 44px;
            display: block;
            padding: 2px;
        }

        .multipleColumn .bd ul li .pic a:hover img {
            border-color: #999;
        }
    </style>
</head>
<body>
<!--头部-->
<%@ include file="/common/header.jsp"%> 

<!--体部-->
<div class="clear"></div>
<div class="jz-beij">
    <ul class="jz-beij-inner">
        <li class="one">信用改变生活，梦想触手可及</li>
        <li class="two">先领工资&nbsp;后兼职&nbsp;嗨翻天！</li>
        <li class="three">工资先到手，干啥都有劲，线上操作自动化，无需抵押和担保</li>
        <li class="three">再也不用担心没钱花了</li>
    </ul>
</div>
<div class="jz-contain">
    <div class="jz-contain-inner">
        <ul>
            <li class="one">找兼职&nbsp;招兼职</li>
            <li class="two">就来励志汪兼职</li>
            <li class="three">励志汪兼职以诚信服务于商家和学生</li>
            <li class="three">现全面覆盖全国近千所高校</li>
            <li class="three">免费为企业提供7×24小时招聘服务</li>
            <li class="four">
                <span style="background: #00a0e9;">
                    <img src="${basePath}/image/seting.png" alt=""/>
                </span>&nbsp;&nbsp;&nbsp;
                <span style="background: #ff8e45;">
                    <img src="${basePath}/image/echart.png" alt=""/>
                </span>&nbsp;&nbsp;&nbsp;
                <span style="background: #e9313f;">
                    <img src="${basePath}/image/perse.png" alt=""/>
                </span>
            </li>
            <li class="five">
                <span>励志汪兼职</span>&nbsp;&nbsp;&nbsp;
                <span>励志汪信用</span>&nbsp;&nbsp;&nbsp;
                <span>预支工资</span>
            </li>
            <li class="six"><a href="">了解更多</a></li>
        </ul>
    </div>
</div>

<div class="jz-advanbtage">
    <div class="jz-advanbtage-center">
        <p>我们的优势</p>

        <p class="eng">Our&nbsp;Advantages</p>

        <div class="jz-advanbtage-center1">
            <dl>
                <dt><img src="${basePath}/image/newzhenshi.png" alt=""/></dt>
                <dd>
                    <h5>信息真实</h5>

                    <p style="color: #adadad;font-size: 18px;font-weight: normal;line-height: 1.2;">励志汪严格审核每一个
                        兼职发布单位，确保我 们的每一份兼职信息都 是真实有效的。</p>
                </dd>
            </dl>
            <dl>
                <dt><img src="${basePath}/image/payshangjia.png" alt=""/></dt>
                <dd>
                    <h5>商家预付</h5>

                    <p style="color: #adadad;font-size: 18px;font-weight: normal;line-height: 1.2;">在励志汪，所有商家招
                        聘需预付薪水至平台， 完工领薪励志汪做得 到，再也不拍黑中介</p>
                </dd>
            </dl>
            <dl>
                <dt><img src="${basePath}/image/dangtianmoney.png" alt=""/></dt>
                <dd>
                    <h5>当天领薪</h5>

                    <p style="color: #adadad;font-size: 18px;font-weight: normal;line-height: 1.2;">励志汪闪电支付采用更
                        有保障的第三方支付平 台，确保工作完成后薪 水当天就到账。</p>
                </dd>
            </dl>
            <dl>
                <dt><img src="${basePath}/image/ewaimoney.png" alt=""/></dt>
                <dd>
                    <h5>额外奖励</h5>

                    <p style="color: #adadad;font-size: 18px;font-weight: normal;line-height: 1.2;">在励志汪，做兼职的次
                        数越多，额外奖励越丰 厚，可谓天道酬勤，一 点不假。</p>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div class="jz-advanbtage jz-office">
    <div class="jz-advanbtage-center jz-office-center">
        <p>我们的分公司</p>

        <p class="eng">Branch&nbsp;Office</p>
        <ul class="clear">
            <li class="one">
                <p><img src="${basePath}/image/fengongsi.png" alt=""/></p>
            <li class="two">
                <img src="${basePath}/image/map_1.png" alt=""/>
            </li>
        </ul>
    </div>
</div>
<div class="jz-advanbtage jz-partner">
    <div class="jz-advanbtage-center">
        <p>我们的合作伙伴</p>

        <p class="eng">Cooperation&nbsp;Partner</p>

        <p><img src="${basePath}/image/partner.png" alt=""/></p>

        <div class="multipleColumn" style="margin:10px auto">
            <div class="hd">
                <a class="next"></a>
                <ul></ul>
                <a class="prev"></a>
                <!--<span class="pageState"></span>-->
            </div>
            <div class="bd">

                <div class="ulWrap">
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/1.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/2.png"/></div>
                        </li>

                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/3.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/4.png"/></div>
                        </li>
                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/5.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/6.png"/></div>
                        </li>
                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/7.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/8.png"/></div>
                        </li>
                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/9.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/10.png"/></div>
                        </li>
                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/11.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/12.png"/></div>
                        </li>
                    </ul>
                    <ul><!-- 把每次滚动的n个li放到1个ul里面 -->
                        <li>
                            <div class="pic"><img src="${basePath}/images/13.png"/></div>
                        </li>
                        <li>
                            <div class="pic"><img src="${basePath}/images/14.png"/></div>
                        </li>
                    </ul>
                </div>
                <!-- ulWrap End -->
            </div>
            <!-- bd End -->
        </div>
    </div>
</div>

<!--底部-->
<%@ include file="/common/footer.jsp"%> 
<script type="text/javascript">
    /*
     多行/多列的滚动解决思路在于：把每次滚动的n个li放到1个ul里面，然后用SuperSlide每次滚动1个ul，相当于每次滚动n个li
     */
    jQuery(".multipleColumn").slide({
        titCell: ".hd ul",
        mainCell: ".bd .ulWrap",
        autoPage: true,
        effect: "leftLoop",
        autoPlay: true,
        vis: 5
    });
</script>
</body>
</html>