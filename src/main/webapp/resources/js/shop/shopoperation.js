$(function () {
    //接收URL中的shopId参数
    var shopId = getQueryString('shopId');
    //如果shopId为空，则表示注册店铺逻辑；如果shopId不为空，则表示修改相应店铺信息逻辑
    var isEdit = shopId?true:false;
    //获取全部店铺所属区域与店铺分类信息URL
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    //实现注册（添加）店铺信息操作的URL
    var registerShopUrl = 'registershop';
    //获取需要修改的店铺信息URL
    var shopInfoUrl = 'getshopbyid?shopId='+shopId;
    //实现修改店铺信息操作的URL
    var editShopUrl = 'modifyshop';

    if (!isEdit){
        getShopInitInfo();
    } else {
        getShopInfo(shopId);
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function(data) {
            if (data.result) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled','disabled');
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
            }
        });

        $('#submit').click(function () {
            var shop = {};
            if (isEdit){
                shop.shopId = shopId;
            }
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual = $('#j_captcha').val();
            if (!verifyCodeActual){
                $.toast('请输入验证码');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            $.ajax({
                url : (isEdit?editShopUrl:registerShopUrl),
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function (data) {
                    if (data.result){
                        $.toast('提交成功！');
                    } else {
                        $.toast('提交失败！' + data.errMsg);
                    }
                    $('#captcha-img').click();
                }
            });
        });
    }
    function getShopInitInfo() {
        $.getJSON(initUrl,function (data) {
            if (data.result) {
                var tempHtml = "";
                var tempAreaHtml = "";
                data.shopCategoryList.map(function (item,index) {
                    tempHtml += '<option data-id="'+item.shopCategoryId+'">'
                        +item.shopCategoryName+'</option>'
                });
                data.areaList.map(function (item,index) {
                    tempAreaHtml += '<option data-id="'+item.areaId+'">'
                        +item.areaName+'</option>'
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });

        $('#submit').click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual = $('#j_captcha').val();
            if (!verifyCodeActual){
                $.toast('请输入验证码');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            $.ajax({
                url : (isEdit?editShopUrl:registerShopUrl),
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function (data) {
                    if (data.result){
                        $.toast('提交成功！');
                    } else {
                        $.toast('提交失败！' + data.errMsg);
                    }
                    $('#captcha-img').click();
                }
            });
        });
    }
})