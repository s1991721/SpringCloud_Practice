<html>
<#include "./common/header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="post" action="/sell/seller/login">
                <div class="form-group">
                    <label>用户名</label><input type="text" class="form-control" name="openid"/>
                </div>
                <div class="form-group">
                    <label>密码</label><input type="password" class="form-control"/>
                </div>

                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>