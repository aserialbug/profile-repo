create table if not exists public.profiles
(
    id         uuid      not null primary key,
    inn_hash   text      not null unique,
    phone      text      not null,
    email      text      not null,
    mfa_id     text      not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version    integer   not null
);

alter table public.profiles
    owner to postgres;
