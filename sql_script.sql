-- 創建 systemUser 表
CREATE TABLE systemUser (
    id SERIAL PRIMARY KEY,
    _id UUID NOT NULL UNIQUE, -- 添加唯一約束
    account VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL
);

-- 創建 goods 表
CREATE TABLE goods (
    id SERIAL PRIMARY KEY,
    _id UUID NOT NULL,
    name VARCHAR(128) NOT NULL,
    cr_user UUID,
    cr_datetime TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    up_user UUID ,
    up_datetime TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cr_user) REFERENCES systemUser (_id), -- 參照具有唯一約束的欄位
    FOREIGN KEY (up_user) REFERENCES systemUser (_id)
);