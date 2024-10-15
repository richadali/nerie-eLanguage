PGDMP     
    *            	    |         	   elearning    14.12    14.12 -    *           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            +           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ,           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            -           1262    85382 	   elearning    DATABASE     e   CREATE DATABASE elearning WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_India.1252';
    DROP DATABASE elearning;
                postgres    false                        2615    85383 	   elearning    SCHEMA        CREATE SCHEMA elearning;
    DROP SCHEMA elearning;
                postgres    false            �            1259    85386    audit_table    TABLE     �   CREATE TABLE elearning.audit_table (
    audit_id bigint NOT NULL,
    action character varying(255),
    entity_id character varying(255),
    entity_name character varying(255),
    id bigint
);
 "   DROP TABLE elearning.audit_table;
    	   elearning         heap    postgres    false    6            �            1259    85385    audit_table_audit_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.audit_table_audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE elearning.audit_table_audit_id_seq;
    	   elearning          postgres    false    6    212            .           0    0    audit_table_audit_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE elearning.audit_table_audit_id_seq OWNED BY elearning.audit_table.audit_id;
       	   elearning          postgres    false    211            �            1259    85394    category    TABLE     �   CREATE TABLE elearning.category (
    category_code character varying(255) NOT NULL,
    category_description character varying(250) NOT NULL
);
    DROP TABLE elearning.category;
    	   elearning         heap    postgres    false    6            �            1259    85402    link    TABLE     �   CREATE TABLE elearning.link (
    id bigint NOT NULL,
    link character varying(250) NOT NULL,
    word_id character varying(255) NOT NULL
);
    DROP TABLE elearning.link;
    	   elearning         heap    postgres    false    6            �            1259    85401    link_id_seq    SEQUENCE     w   CREATE SEQUENCE elearning.link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE elearning.link_id_seq;
    	   elearning          postgres    false    215    6            /           0    0    link_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE elearning.link_id_seq OWNED BY elearning.link.id;
       	   elearning          postgres    false    214            �            1259    85410    login_activities    TABLE     �   CREATE TABLE elearning.login_activities (
    id bigint NOT NULL,
    ip_address character varying(255),
    is_success boolean,
    "time" timestamp(6) without time zone,
    userid uuid
);
 '   DROP TABLE elearning.login_activities;
    	   elearning         heap    postgres    false    6            �            1259    85384 	   login_seq    SEQUENCE     u   CREATE SEQUENCE elearning.login_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE elearning.login_seq;
    	   elearning          postgres    false    6            �            1259    85416    task_allotment    TABLE     x   CREATE TABLE elearning.task_allotment (
    id bigint NOT NULL,
    user_id uuid,
    task_id character varying(255)
);
 %   DROP TABLE elearning.task_allotment;
    	   elearning         heap    postgres    false    6            �            1259    85415    task_allotment_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.task_allotment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE elearning.task_allotment_id_seq;
    	   elearning          postgres    false    6    218            0           0    0    task_allotment_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE elearning.task_allotment_id_seq OWNED BY elearning.task_allotment.id;
       	   elearning          postgres    false    217            �            1259    85422    tasks    TABLE     �   CREATE TABLE elearning.tasks (
    task_id character varying(255) NOT NULL,
    task_description character varying(255) NOT NULL
);
    DROP TABLE elearning.tasks;
    	   elearning         heap    postgres    false    6            �            1259    85429    user_information    TABLE       CREATE TABLE elearning.user_information (
    id uuid NOT NULL,
    contact_no character varying(255) NOT NULL,
    designation character varying(100) NOT NULL,
    office character varying(250) NOT NULL,
    office_address character varying(250) NOT NULL
);
 '   DROP TABLE elearning.user_information;
    	   elearning         heap    postgres    false    6            �            1259    85436    users    TABLE     �  CREATE TABLE elearning.users (
    id uuid NOT NULL,
    account_non_expired boolean,
    account_non_locked boolean,
    credentials_non_expired boolean,
    date_joined timestamp(6) without time zone,
    enabled boolean,
    first_name character varying(50) NOT NULL,
    is_superuser boolean,
    last_name character varying(50) NOT NULL,
    middle_name character varying(255),
    password character varying(250) NOT NULL,
    role character varying(255),
    username character varying(255) NOT NULL
);
    DROP TABLE elearning.users;
    	   elearning         heap    postgres    false    6            |           2604    85389    audit_table audit_id    DEFAULT     �   ALTER TABLE ONLY elearning.audit_table ALTER COLUMN audit_id SET DEFAULT nextval('elearning.audit_table_audit_id_seq'::regclass);
 F   ALTER TABLE elearning.audit_table ALTER COLUMN audit_id DROP DEFAULT;
    	   elearning          postgres    false    212    211    212            }           2604    85405    link id    DEFAULT     h   ALTER TABLE ONLY elearning.link ALTER COLUMN id SET DEFAULT nextval('elearning.link_id_seq'::regclass);
 9   ALTER TABLE elearning.link ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    215    214    215            ~           2604    85419    task_allotment id    DEFAULT     |   ALTER TABLE ONLY elearning.task_allotment ALTER COLUMN id SET DEFAULT nextval('elearning.task_allotment_id_seq'::regclass);
 C   ALTER TABLE elearning.task_allotment ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    217    218    218                      0    85386    audit_table 
   TABLE DATA           V   COPY elearning.audit_table (audit_id, action, entity_id, entity_name, id) FROM stdin;
 	   elearning          postgres    false    212   O4                 0    85394    category 
   TABLE DATA           J   COPY elearning.category (category_code, category_description) FROM stdin;
 	   elearning          postgres    false    213   l4       !          0    85402    link 
   TABLE DATA           4   COPY elearning.link (id, link, word_id) FROM stdin;
 	   elearning          postgres    false    215   �4       "          0    85410    login_activities 
   TABLE DATA           Y   COPY elearning.login_activities (id, ip_address, is_success, "time", userid) FROM stdin;
 	   elearning          postgres    false    216   �4       $          0    85416    task_allotment 
   TABLE DATA           A   COPY elearning.task_allotment (id, user_id, task_id) FROM stdin;
 	   elearning          postgres    false    218   5       %          0    85422    tasks 
   TABLE DATA           =   COPY elearning.tasks (task_id, task_description) FROM stdin;
 	   elearning          postgres    false    219   )5       &          0    85429    user_information 
   TABLE DATA           b   COPY elearning.user_information (id, contact_no, designation, office, office_address) FROM stdin;
 	   elearning          postgres    false    220   F5       '          0    85436    users 
   TABLE DATA           �   COPY elearning.users (id, account_non_expired, account_non_locked, credentials_non_expired, date_joined, enabled, first_name, is_superuser, last_name, middle_name, password, role, username) FROM stdin;
 	   elearning          postgres    false    221   �5       1           0    0    audit_table_audit_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('elearning.audit_table_audit_id_seq', 1, false);
       	   elearning          postgres    false    211            2           0    0    link_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('elearning.link_id_seq', 1, false);
       	   elearning          postgres    false    214            3           0    0 	   login_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('elearning.login_seq', 2, true);
       	   elearning          postgres    false    210            4           0    0    task_allotment_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('elearning.task_allotment_id_seq', 1, false);
       	   elearning          postgres    false    217            �           2606    85393    audit_table audit_table_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY elearning.audit_table
    ADD CONSTRAINT audit_table_pkey PRIMARY KEY (audit_id);
 I   ALTER TABLE ONLY elearning.audit_table DROP CONSTRAINT audit_table_pkey;
    	   elearning            postgres    false    212            �           2606    85400    category category_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY elearning.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_code);
 C   ALTER TABLE ONLY elearning.category DROP CONSTRAINT category_pkey;
    	   elearning            postgres    false    213            �           2606    85409    link link_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY elearning.link
    ADD CONSTRAINT link_pkey PRIMARY KEY (id);
 ;   ALTER TABLE ONLY elearning.link DROP CONSTRAINT link_pkey;
    	   elearning            postgres    false    215            �           2606    85414 &   login_activities login_activities_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY elearning.login_activities
    ADD CONSTRAINT login_activities_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY elearning.login_activities DROP CONSTRAINT login_activities_pkey;
    	   elearning            postgres    false    216            �           2606    85421 "   task_allotment task_allotment_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY elearning.task_allotment
    ADD CONSTRAINT task_allotment_pkey PRIMARY KEY (id);
 O   ALTER TABLE ONLY elearning.task_allotment DROP CONSTRAINT task_allotment_pkey;
    	   elearning            postgres    false    218            �           2606    85428    tasks tasks_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY elearning.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (task_id);
 =   ALTER TABLE ONLY elearning.tasks DROP CONSTRAINT tasks_pkey;
    	   elearning            postgres    false    219            �           2606    85435 &   user_information user_information_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY elearning.user_information
    ADD CONSTRAINT user_information_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY elearning.user_information DROP CONSTRAINT user_information_pkey;
    	   elearning            postgres    false    220            �           2606    85442    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY elearning.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY elearning.users DROP CONSTRAINT users_pkey;
    	   elearning            postgres    false    221            �           2606    85443 '   audit_table fkba7ryvuqmap66o2e5nm93d0ct    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.audit_table
    ADD CONSTRAINT fkba7ryvuqmap66o2e5nm93d0ct FOREIGN KEY (id) REFERENCES elearning.login_activities(id);
 T   ALTER TABLE ONLY elearning.audit_table DROP CONSTRAINT fkba7ryvuqmap66o2e5nm93d0ct;
    	   elearning          postgres    false    3206    216    212            �           2606    85448 *   task_allotment fkogbnye7nb3lpw3m64u14wdyai    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.task_allotment
    ADD CONSTRAINT fkogbnye7nb3lpw3m64u14wdyai FOREIGN KEY (task_id) REFERENCES elearning.tasks(task_id);
 W   ALTER TABLE ONLY elearning.task_allotment DROP CONSTRAINT fkogbnye7nb3lpw3m64u14wdyai;
    	   elearning          postgres    false    218    3210    219                  x������ � �            x������ � �      !      x������ � �      "   V   x��̱�0�:���?1X���R��/Z:���b��.4v�)|�1`���K��159�Msr�D��9�ߟ����"� �'$      $      x������ � �      %      x������ � �      &   Q   x��H��05�H�M4J1�5I�0�ML12�M4LLM5M30526�4��4�43516�tq���s�tU������K������ ��
      '   �   x�5��
�@F��S�p;5��q�e����"��qp"���a|�s8��`�f��B�ڰ-�T7��D2N��7F���`*W��	�|��]�l��.�.%nT�ݴ	���>��PVU�I�0��iA�W�s=2>_U����G'ON�^��ʃ�|�,��F} �21�     