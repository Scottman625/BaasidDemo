資料庫建立table: 運行sql.script

本機端運行 DemoApplication

# 註冊User
http://127.0.0.1:8080/user/register/ 
body帶參數 account password name 皆為varchar

# User 登入
http://127.0.0.1:8080/auth/login
body帶參數 account password

(以下header皆需帶入token)
# 新增商品 (POST)
http://localhost:8080/goods/add
 body帶goods_name

# 查詢所有商品 (GET)
http://localhost:8080/goods

# 查詢指定ID商品 (GET)
http://localhost:8080/goods/{goodsId}/

# 更新商品 (PUT)
http://localhost:8080/goods/update/{goodsId}/
body帶goods_name

# 刪除商品 (DELETE)
http://localhost:8080/goods/delete/{goodsId}/

# Swagger-UI介面
http://localhost:8080/swagger-ui/index.html