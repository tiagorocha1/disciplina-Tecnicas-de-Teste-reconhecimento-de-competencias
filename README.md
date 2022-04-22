# Projeto para obtenção de reconhecimento de competências

## IFPB - Campus Monteiro    
<strong> Curso: </strong> Análise e Desenvolvimento de Sistemas </br>
<strong> Disciplina: </strong> Técnicas de Testes </br>
<strong> Professor: </strong> Tiago Brasileiro </br>
<strong> Aluno: </strong> Tiago Rocha </br>
<strong> Período: </strong> 2022.1 </br>

##

### Avaliação - Requisitos

  O projeto a ser testado será o mesmo desenvolvido na disciplina de DAC.
  </br>
  </br>
  <strong>Tópicos a serem avaliados:</strong>
   - Testes de Unidade (Junit)
   - Testes de Integração (Junit)
   - Testes de Sistema (pode usar Selenium e Junit)
   - Testes de Aceitação (pode ser um documento descritivo ou o planejamento durante a apresentação)

  Os testes serão avaliados durante a apresentação (que deverá durar no máximo 30 min).

## Screenshots

![page01](https://user-images.githubusercontent.com/8715162/163701535-da1ae346-9693-468f-abeb-ef14b03f6107.PNG)

![page02](https://user-images.githubusercontent.com/8715162/163701538-051b71c8-3d93-47bd-bf5c-c6ae56544434.PNG)

![page3](https://user-images.githubusercontent.com/8715162/163701546-3951a868-07d5-4f30-a233-50b34339b6a5.PNG)

 ### Trainee Tasks
_A system for recording tasks_

![N|Solid](https://pivotal.gallerycdn.vsassets.io/extensions/pivotal/vscode-boot-dev-pack/0.1.0/1629148309593/Microsoft.VisualStudio.Services.Icons.Default)
![N|Solid](https://raw.githubusercontent.com/GozAttila/GozAttila/main/assets/4_React.png)



[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

##
 ### Instalação
 
 #### 1) Executar o script para configuração inicial do Banco - Banco de dados MySQL
 Usuário padrão <strong>admin</strong> com  senha <strong>123</strong>

      -- Copiando estrutura do banco de dados para trainee
      CREATE DATABASE IF NOT EXISTS `trainee` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
      USE `trainee`;

      -- Copiando estrutura para tabela trainee_bk.category
      CREATE TABLE IF NOT EXISTS `category` (
        `id` bigint(20) NOT NULL,
        `name` varchar(255) DEFAULT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.category: ~5 rows (aproximadamente)
      /*!40000 ALTER TABLE `category` DISABLE KEYS */;
      INSERT INTO `category` (`id`, `name`) VALUES
        (1, 'USER SUPPORT'),
        (2, 'LABORATORY SUPPORT'),
        (3, 'DIGITAL CONTENT CREATION'),
        (4, 'PC MAINTENANCE'),
        (5, 'NETWORK MAINTENANCE');
      /*!40000 ALTER TABLE `category` ENABLE KEYS */;

      -- Copiando estrutura para tabela trainee_bk.configuration
      CREATE TABLE IF NOT EXISTS `configuration` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `category_config` bit(1) NOT NULL,
        `user_admin_config` bit(1) NOT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.configuration: ~0 rows (aproximadamente)
      /*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
      /*!40000 ALTER TABLE `configuration` ENABLE KEYS */;

      -- Copiando estrutura para tabela trainee_bk.profile
      CREATE TABLE IF NOT EXISTS `profile` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `name` varchar(255) DEFAULT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.profile: ~2 rows (aproximadamente)
      /*!40000 ALTER TABLE `profile` DISABLE KEYS */;
      INSERT INTO `profile` (`id`, `name`) VALUES
        (1, 'ROLE_ALUNO'),
        (2, 'ROLE_ADMINISTRADOR');
      /*!40000 ALTER TABLE `profile` ENABLE KEYS */;

      -- Copiando estrutura para tabela trainee_bk.task
      CREATE TABLE IF NOT EXISTS `task` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `creation_date` datetime(6) DEFAULT NULL,
        `description` varchar(255) DEFAULT NULL,
        `done` bit(1) NOT NULL,
        `finish_date` datetime(6) DEFAULT NULL,
        `user_id` bigint(20) DEFAULT NULL,
        `category_id` bigint(20) DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`),
        KEY `FKkjb4pwpo8oqc8fvkgbmiitsu9` (`category_id`),
        CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
        CONSTRAINT `FKkjb4pwpo8oqc8fvkgbmiitsu9` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.task: ~8 rows (aproximadamente)
      /*!40000 ALTER TABLE `task` DISABLE KEYS */;
      INSERT INTO `task` (`id`, `creation_date`, `description`, `done`, `finish_date`, `user_id`, `category_id`) VALUES
        (233, '2022-04-16 19:11:15.000000', 'aluno task 03', b'0', NULL, 2, 1),
        (237, '2022-04-17 01:42:41.000000', 'eeeee', b'0', NULL, 2, 4),
        (238, '2022-04-17 01:42:44.000000', 'hjhjhj', b'0', NULL, 2, 4),
        (248, '2022-04-17 01:48:32.000000', 'nova', b'0', NULL, 2, 1),
        (249, '2022-04-17 02:14:34.000000', 'the printer crashed', b'0', NULL, 1, 1),
        (250, '2022-04-17 02:15:02.000000', 'replace network cables', b'0', NULL, 1, 2),
        (251, '2022-04-17 02:15:24.000000', 'format computer 5674', b'0', NULL, 1, 4),
        (252, '2022-04-17 02:15:55.000000', 'replace D block wireless router', b'0', NULL, 1, 5),
        (253, '2022-04-17 02:16:22.000000', 'create password recovery tutorial', b'0', NULL, 1, 3);
      /*!40000 ALTER TABLE `task` ENABLE KEYS */;

      -- Copiando estrutura para tabela trainee_bk.user
      CREATE TABLE IF NOT EXISTS `user` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `email` varchar(255) DEFAULT NULL,
        `name` varchar(255) DEFAULT NULL,
        `password` varchar(255) DEFAULT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.user: ~2 rows (aproximadamente)
      /*!40000 ALTER TABLE `user` DISABLE KEYS */;
      INSERT INTO `user` (`id`, `email`, `name`, `password`) VALUES
        (1, 'admin@task', 'admin', '$2a$10$.fE23/l3rJnjIK05biQFUuqqLuhbN5BnzHZ25vdh5kl9dmxdIKgBC'),
        (2, 'aluno@task', 'aluno', '$2a$10$.fE23/l3rJnjIK05biQFUuqqLuhbN5BnzHZ25vdh5kl9dmxdIKgBC');
      /*!40000 ALTER TABLE `user` ENABLE KEYS */;

      -- Copiando estrutura para tabela trainee_bk.user_profiles
      CREATE TABLE IF NOT EXISTS `user_profiles` (
        `user_id` bigint(20) NOT NULL,
        `profiles_id` bigint(20) NOT NULL,
        KEY `FK7bauo77qnjbrc8yjqwrlc8o5k` (`profiles_id`),
        KEY `FK4np1ktrt8iwtncc4otmhfd6yn` (`user_id`),
        CONSTRAINT `FK4np1ktrt8iwtncc4otmhfd6yn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
        CONSTRAINT `FK7bauo77qnjbrc8yjqwrlc8o5k` FOREIGN KEY (`profiles_id`) REFERENCES `profile` (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

      -- Copiando dados para a tabela trainee_bk.user_profiles: ~0 rows (aproximadamente)
      /*!40000 ALTER TABLE `user_profiles` DISABLE KEYS */;
      INSERT INTO `user_profiles` (`user_id`, `profiles_id`) VALUES
        (1, 2),
        (2, 1);
      /*!40000 ALTER TABLE `user_profiles` ENABLE KEYS */;

      /*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
      /*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
      /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
      /*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
'
    
#### 2) Deploy BackEnd
Importar o projeto na sua IDE de preferência, no Spring Tools Suite:</br>
<strong> File -> Import -> Git -> Project from Git (with smart import). </strong> </br>
Finalizada a importação,  no STS, clicar com o botão direito: run as -> Java Application. 

    https://github.com/tiagorocha1/disciplina-dac-reconhecimento-de-competencias.git


#### 3) Deply FronEnd
  1) Instalar o node.js (https://nodejs.org/en/download/)
  2) Clonar o projeto 
  3) Entrar na pasta FrontEnd
  4) Executar o comando npm install (instala todas as dependências configuradas no arquivos package.json)
  5) Executar o comando npm run dev (roda o projeto na porta 8081 --> http://localhost:8081/?#/tasks ) 
