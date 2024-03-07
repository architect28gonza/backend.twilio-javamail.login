create table if not exists tbl_user(
    use_id BIGSERIAL primary key,
    use_username varchar(100) not null,
    use_password varchar(100) not null,
    use_email varchar(100) not null,
    use_phone varchar(30) not null,
    use_role varchar(20) not null,
    use_register timestamp default now()
);