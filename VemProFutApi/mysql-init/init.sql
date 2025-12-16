-- Cria o usuário
CREATE USER IF NOT EXISTS 'vpfadm'@'%' IDENTIFIED BY 'root123';

-- Dá permissões no banco
GRANT ALL PRIVILEGES ON vemprofutdb.* TO 'vpfadm'@'%';

-- Atualiza permissões
FLUSH PRIVILEGES;