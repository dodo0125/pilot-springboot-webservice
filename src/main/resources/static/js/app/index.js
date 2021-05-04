var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });


        $('#btn-save-service').on('click', function () {
             _this.saveService();
        });
        $('#btn-update-service').on('click', function () {
             _this.updateService();
        });
        $('#btn-delete-service').on('click', function () {
             _this.deleteService();
        });


        $('#btn-search').on('click', function () {
            _this.search();
        });

        $('#btn-search-info').on('click', function () {
            _this.searchInfo();
        });



        $('#btn-save-product').on('click', function () {
             _this.saveProduct();
        });
        $('#btn-update-product').on('click', function () {
             _this.updateProduct();
        });
        $('#btn-delete-product').on('click', function () {
             _this.deleteProduct();
        });


        $('#btn-save-subscribe').on('click', function () {
             _this.saveSubscribe();
        });
        $('#btn-search-subscribe').on('click', function () {
             _this.searchSubscribe();
        });


    },
    save : function () {
        var data = {
            customername: $('#customername').val(),
            age: $('#age').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('고객정보가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            customername: $('#customername').val(),
            age: $('#age').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/customer/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('고객정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/customer/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('고객정보가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    saveService : function () {
            var data = {
                svcnum: $('#svcnum').val(),
                customerid: $('#customerid').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/sktservice',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('서비스정보가 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    updateService : function () {
            var data = {
                svcnum: $('#svcnum').val(),
                customerid: $('#customerid').val()
            };

            var id = $('#id').val();

            $.ajax({
                type: 'PUT',
                url: '/api/v1/sktservice/'+id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('서비스정보가 수정되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    deleteService : function () {
            var id = $('#id').val();

            $.ajax({
                type: 'DELETE',
                url: '/api/v1/sktservice/'+id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('서비스정보가 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    search : function () {
            //var customername = document.getElementById("customername").value;
            var customername = $("#customername").val();

            $.ajax({
                type: 'GET',
                url: '/api/v1/customer/'+ customername +'/search',
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                window.location.href = '/'+'customer/'+ customername +'/search';
                alert('고객정보가 조회되었습니다.' );

            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    searchInfo : function () {
            var customername = $("#customername").val();

            $.ajax({
                type: 'GET',
                url: '/api/v1/customer/'+ customername +'/searchinfo',
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                window.location.href = '/'+'customer/'+ customername +'/searchinfo';
                alert('고객님의 서비스정보가 조회되었습니다.' );

            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    saveProduct : function () {
        var data = {
                productname: $('#productname').val(),
                fee: $('#fee').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/product',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
               alert('신규 상품이 등록되었습니다.');
               window.location.href = '/';
            }).fail(function (error) {
               alert(JSON.stringify(error));
            });
    },
    updateProduct : function () {
        var data = {
            productname: $('#productname').val(),
            fee: $('#fee').val()
            };

            var productid = $('#productid').val();

            $.ajax({
                type: 'PUT',
                url: '/api/v1/product/'+productid,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('상품정보가 수정되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    deleteProduct : function () {
        var id = $('#productid').val();

            $.ajax({
                type: 'DELETE',
                url: '/api/v1/product/'+productid,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('상품정보가 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    saveSubscribe : function () {
        var data = {
                productid: $('#productid').val(),
                serviceid: $('#serviceid').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/subscribe',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
               alert('상품이 가입 완료되었습니다.');
               window.location.href = '/';
            }).fail(function (error) {
               alert(JSON.stringify(error));
            });
    },
    searchSubscribe : function () {
                var serviceid = $("#serviceid").val();

                $.ajax({
                    type: 'GET',
                    url: '/api/v1/subscribe/'+ serviceid +'/search',
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8'
                }).done(function() {
                    window.location.href = '/'+'subscribe/'+ serviceid +'/search';
                    alert('고객님의 상품 가입정보가 조회되었습니다.' );

                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
    }
};

main.init();