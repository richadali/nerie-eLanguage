PGDMP     -                	    |         	   elearning    14.13    14.13 c    q           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            r           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            s           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            t           1262    24720 	   elearning    DATABASE     e   CREATE DATABASE elearning WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_India.utf8';
    DROP DATABASE elearning;
                postgres    false                        2615    24721 	   elearning    SCHEMA        CREATE SCHEMA elearning;
    DROP SCHEMA elearning;
                postgres    false            �            1259    24722    audit_table    TABLE     �   CREATE TABLE elearning.audit_table (
    audit_id bigint NOT NULL,
    action character varying(255),
    entity_id character varying(255),
    entity_name character varying(255),
    id bigint
);
 "   DROP TABLE elearning.audit_table;
    	   elearning         heap    postgres    false    4            �            1259    24727    audit_table_audit_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.audit_table_audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE elearning.audit_table_audit_id_seq;
    	   elearning          postgres    false    210    4            u           0    0    audit_table_audit_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE elearning.audit_table_audit_id_seq OWNED BY elearning.audit_table.audit_id;
       	   elearning          postgres    false    211            �            1259    24799    category    TABLE     �   CREATE TABLE elearning.category (
    id bigint NOT NULL,
    category_name character varying(250) NOT NULL,
    main_category_id bigint
);
    DROP TABLE elearning.category;
    	   elearning         heap    postgres    false    4            �            1259    24798    category_id_seq    SEQUENCE     {   CREATE SEQUENCE elearning.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE elearning.category_id_seq;
    	   elearning          postgres    false    4    224            v           0    0    category_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE elearning.category_id_seq OWNED BY elearning.category.id;
       	   elearning          postgres    false    223            �            1259    24842    english_words_sentences    TABLE     �   CREATE TABLE elearning.english_words_sentences (
    id bigint NOT NULL,
    english_word character varying(255) NOT NULL,
    category_id bigint,
    sub_category_id bigint
);
 .   DROP TABLE elearning.english_words_sentences;
    	   elearning         heap    postgres    false    4            �            1259    24841    english_words_sentences_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.english_words_sentences_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE elearning.english_words_sentences_id_seq;
    	   elearning          postgres    false    4    232            w           0    0    english_words_sentences_id_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE elearning.english_words_sentences_id_seq OWNED BY elearning.english_words_sentences.id;
       	   elearning          postgres    false    231            �            1259    24830    language    TABLE     �   CREATE TABLE elearning.language (
    id bigint NOT NULL,
    language_name character varying(250) NOT NULL,
    state_id bigint
);
    DROP TABLE elearning.language;
    	   elearning         heap    postgres    false    4            �            1259    24829    language_id_seq    SEQUENCE     {   CREATE SEQUENCE elearning.language_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE elearning.language_id_seq;
    	   elearning          postgres    false    4    230            x           0    0    language_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE elearning.language_id_seq OWNED BY elearning.language.id;
       	   elearning          postgres    false    229            �            1259    24733    link    TABLE     �   CREATE TABLE elearning.link (
    id bigint NOT NULL,
    link character varying(250) NOT NULL,
    word_id character varying(255) NOT NULL
);
    DROP TABLE elearning.link;
    	   elearning         heap    postgres    false    4            �            1259    24738    link_id_seq    SEQUENCE     w   CREATE SEQUENCE elearning.link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE elearning.link_id_seq;
    	   elearning          postgres    false    4    212            y           0    0    link_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE elearning.link_id_seq OWNED BY elearning.link.id;
       	   elearning          postgres    false    213            �            1259    24739    login_activities    TABLE     �   CREATE TABLE elearning.login_activities (
    id bigint NOT NULL,
    ip_address character varying(255),
    is_success boolean,
    "time" timestamp(6) without time zone,
    userid uuid
);
 '   DROP TABLE elearning.login_activities;
    	   elearning         heap    postgres    false    4            �            1259    24742 	   login_seq    SEQUENCE     u   CREATE SEQUENCE elearning.login_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE elearning.login_seq;
    	   elearning          postgres    false    4            �            1259    24792    main_category    TABLE     t   CREATE TABLE elearning.main_category (
    id bigint NOT NULL,
    category_name character varying(250) NOT NULL
);
 $   DROP TABLE elearning.main_category;
    	   elearning         heap    postgres    false    4            �            1259    24791    main_category_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.main_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE elearning.main_category_id_seq;
    	   elearning          postgres    false    4    222            z           0    0    main_category_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE elearning.main_category_id_seq OWNED BY elearning.main_category.id;
       	   elearning          postgres    false    221            �            1259    24823    state    TABLE     i   CREATE TABLE elearning.state (
    id bigint NOT NULL,
    state_name character varying(250) NOT NULL
);
    DROP TABLE elearning.state;
    	   elearning         heap    postgres    false    4            �            1259    24822    state_id_seq    SEQUENCE     x   CREATE SEQUENCE elearning.state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE elearning.state_id_seq;
    	   elearning          postgres    false    228    4            {           0    0    state_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE elearning.state_id_seq OWNED BY elearning.state.id;
       	   elearning          postgres    false    227            �            1259    24811    sub_category    TABLE     �   CREATE TABLE elearning.sub_category (
    id bigint NOT NULL,
    sub_category_name character varying(250) NOT NULL,
    category_id bigint
);
 #   DROP TABLE elearning.sub_category;
    	   elearning         heap    postgres    false    4            �            1259    24810    sub_category_id_seq    SEQUENCE        CREATE SEQUENCE elearning.sub_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE elearning.sub_category_id_seq;
    	   elearning          postgres    false    226    4            |           0    0    sub_category_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE elearning.sub_category_id_seq OWNED BY elearning.sub_category.id;
       	   elearning          postgres    false    225            �            1259    24743    task_allotment    TABLE     x   CREATE TABLE elearning.task_allotment (
    id bigint NOT NULL,
    user_id uuid,
    task_id character varying(255)
);
 %   DROP TABLE elearning.task_allotment;
    	   elearning         heap    postgres    false    4            �            1259    24746    task_allotment_id_seq    SEQUENCE     �   CREATE SEQUENCE elearning.task_allotment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE elearning.task_allotment_id_seq;
    	   elearning          postgres    false    4    216            }           0    0    task_allotment_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE elearning.task_allotment_id_seq OWNED BY elearning.task_allotment.id;
       	   elearning          postgres    false    217            �            1259    24747    tasks    TABLE     �   CREATE TABLE elearning.tasks (
    task_id character varying(255) NOT NULL,
    task_description character varying(255) NOT NULL
);
    DROP TABLE elearning.tasks;
    	   elearning         heap    postgres    false    4            �            1259    24859    translation    TABLE     )  CREATE TABLE elearning.translation (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    translated_word character varying(255) NOT NULL,
    audio character varying(255),
    video character varying(255),
    eng_word_id bigint,
    language_id bigint,
    user_id uuid
);
 "   DROP TABLE elearning.translation;
    	   elearning         heap    postgres    false    4            �            1259    24858    translation_id_seq    SEQUENCE     ~   CREATE SEQUENCE elearning.translation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE elearning.translation_id_seq;
    	   elearning          postgres    false    4    234            ~           0    0    translation_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE elearning.translation_id_seq OWNED BY elearning.translation.id;
       	   elearning          postgres    false    233            �            1259    24752    user_information    TABLE       CREATE TABLE elearning.user_information (
    id uuid NOT NULL,
    contact_no character varying(255) NOT NULL,
    designation character varying(100) NOT NULL,
    office character varying(250) NOT NULL,
    office_address character varying(250) NOT NULL
);
 '   DROP TABLE elearning.user_information;
    	   elearning         heap    postgres    false    4            �            1259    24757    users    TABLE     �  CREATE TABLE elearning.users (
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
    	   elearning         heap    postgres    false    4            �           2604    24762    audit_table audit_id    DEFAULT     �   ALTER TABLE ONLY elearning.audit_table ALTER COLUMN audit_id SET DEFAULT nextval('elearning.audit_table_audit_id_seq'::regclass);
 F   ALTER TABLE elearning.audit_table ALTER COLUMN audit_id DROP DEFAULT;
    	   elearning          postgres    false    211    210            �           2604    24802    category id    DEFAULT     p   ALTER TABLE ONLY elearning.category ALTER COLUMN id SET DEFAULT nextval('elearning.category_id_seq'::regclass);
 =   ALTER TABLE elearning.category ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    224    223    224            �           2604    24845    english_words_sentences id    DEFAULT     �   ALTER TABLE ONLY elearning.english_words_sentences ALTER COLUMN id SET DEFAULT nextval('elearning.english_words_sentences_id_seq'::regclass);
 L   ALTER TABLE elearning.english_words_sentences ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    231    232    232            �           2604    24833    language id    DEFAULT     p   ALTER TABLE ONLY elearning.language ALTER COLUMN id SET DEFAULT nextval('elearning.language_id_seq'::regclass);
 =   ALTER TABLE elearning.language ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    229    230    230            �           2604    24763    link id    DEFAULT     h   ALTER TABLE ONLY elearning.link ALTER COLUMN id SET DEFAULT nextval('elearning.link_id_seq'::regclass);
 9   ALTER TABLE elearning.link ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    213    212            �           2604    24795    main_category id    DEFAULT     z   ALTER TABLE ONLY elearning.main_category ALTER COLUMN id SET DEFAULT nextval('elearning.main_category_id_seq'::regclass);
 B   ALTER TABLE elearning.main_category ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    221    222    222            �           2604    24826    state id    DEFAULT     j   ALTER TABLE ONLY elearning.state ALTER COLUMN id SET DEFAULT nextval('elearning.state_id_seq'::regclass);
 :   ALTER TABLE elearning.state ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    227    228    228            �           2604    24814    sub_category id    DEFAULT     x   ALTER TABLE ONLY elearning.sub_category ALTER COLUMN id SET DEFAULT nextval('elearning.sub_category_id_seq'::regclass);
 A   ALTER TABLE elearning.sub_category ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    226    225    226            �           2604    24764    task_allotment id    DEFAULT     |   ALTER TABLE ONLY elearning.task_allotment ALTER COLUMN id SET DEFAULT nextval('elearning.task_allotment_id_seq'::regclass);
 C   ALTER TABLE elearning.task_allotment ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    217    216            �           2604    24862    translation id    DEFAULT     v   ALTER TABLE ONLY elearning.translation ALTER COLUMN id SET DEFAULT nextval('elearning.translation_id_seq'::regclass);
 @   ALTER TABLE elearning.translation ALTER COLUMN id DROP DEFAULT;
    	   elearning          postgres    false    233    234    234            V          0    24722    audit_table 
   TABLE DATA           V   COPY elearning.audit_table (audit_id, action, entity_id, entity_name, id) FROM stdin;
 	   elearning          postgres    false    210   +v       d          0    24799    category 
   TABLE DATA           J   COPY elearning.category (id, category_name, main_category_id) FROM stdin;
 	   elearning          postgres    false    224   �v       l          0    24842    english_words_sentences 
   TABLE DATA           d   COPY elearning.english_words_sentences (id, english_word, category_id, sub_category_id) FROM stdin;
 	   elearning          postgres    false    232    w       j          0    24830    language 
   TABLE DATA           B   COPY elearning.language (id, language_name, state_id) FROM stdin;
 	   elearning          postgres    false    230   =w       X          0    24733    link 
   TABLE DATA           4   COPY elearning.link (id, link, word_id) FROM stdin;
 	   elearning          postgres    false    212   Zw       Z          0    24739    login_activities 
   TABLE DATA           Y   COPY elearning.login_activities (id, ip_address, is_success, "time", userid) FROM stdin;
 	   elearning          postgres    false    214   ww       b          0    24792    main_category 
   TABLE DATA           =   COPY elearning.main_category (id, category_name) FROM stdin;
 	   elearning          postgres    false    222   Ix       h          0    24823    state 
   TABLE DATA           2   COPY elearning.state (id, state_name) FROM stdin;
 	   elearning          postgres    false    228   �x       f          0    24811    sub_category 
   TABLE DATA           M   COPY elearning.sub_category (id, sub_category_name, category_id) FROM stdin;
 	   elearning          postgres    false    226   �x       \          0    24743    task_allotment 
   TABLE DATA           A   COPY elearning.task_allotment (id, user_id, task_id) FROM stdin;
 	   elearning          postgres    false    216   My       ^          0    24747    tasks 
   TABLE DATA           =   COPY elearning.tasks (task_id, task_description) FROM stdin;
 	   elearning          postgres    false    218   jy       n          0    24859    translation 
   TABLE DATA           z   COPY elearning.translation (id, created_at, translated_word, audio, video, eng_word_id, language_id, user_id) FROM stdin;
 	   elearning          postgres    false    234   �y       _          0    24752    user_information 
   TABLE DATA           b   COPY elearning.user_information (id, contact_no, designation, office, office_address) FROM stdin;
 	   elearning          postgres    false    219   �y       `          0    24757    users 
   TABLE DATA           �   COPY elearning.users (id, account_non_expired, account_non_locked, credentials_non_expired, date_joined, enabled, first_name, is_superuser, last_name, middle_name, password, role, username) FROM stdin;
 	   elearning          postgres    false    220   4z                  0    0    audit_table_audit_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('elearning.audit_table_audit_id_seq', 2, true);
       	   elearning          postgres    false    211            �           0    0    category_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('elearning.category_id_seq', 10, true);
       	   elearning          postgres    false    223            �           0    0    english_words_sentences_id_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('elearning.english_words_sentences_id_seq', 1, false);
       	   elearning          postgres    false    231            �           0    0    language_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('elearning.language_id_seq', 1, false);
       	   elearning          postgres    false    229            �           0    0    link_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('elearning.link_id_seq', 1, false);
       	   elearning          postgres    false    213            �           0    0 	   login_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('elearning.login_seq', 13, true);
       	   elearning          postgres    false    215            �           0    0    main_category_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('elearning.main_category_id_seq', 8, true);
       	   elearning          postgres    false    221            �           0    0    state_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('elearning.state_id_seq', 1, false);
       	   elearning          postgres    false    227            �           0    0    sub_category_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('elearning.sub_category_id_seq', 8, true);
       	   elearning          postgres    false    225            �           0    0    task_allotment_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('elearning.task_allotment_id_seq', 1, false);
       	   elearning          postgres    false    217            �           0    0    translation_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('elearning.translation_id_seq', 1, false);
       	   elearning          postgres    false    233            �           2606    24766    audit_table audit_table_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY elearning.audit_table
    ADD CONSTRAINT audit_table_pkey PRIMARY KEY (audit_id);
 I   ALTER TABLE ONLY elearning.audit_table DROP CONSTRAINT audit_table_pkey;
    	   elearning            postgres    false    210            �           2606    24804    category category_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY elearning.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY elearning.category DROP CONSTRAINT category_pkey;
    	   elearning            postgres    false    224            �           2606    24847 4   english_words_sentences english_words_sentences_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY elearning.english_words_sentences
    ADD CONSTRAINT english_words_sentences_pkey PRIMARY KEY (id);
 a   ALTER TABLE ONLY elearning.english_words_sentences DROP CONSTRAINT english_words_sentences_pkey;
    	   elearning            postgres    false    232            �           2606    24835    language language_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY elearning.language
    ADD CONSTRAINT language_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY elearning.language DROP CONSTRAINT language_pkey;
    	   elearning            postgres    false    230            �           2606    24770    link link_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY elearning.link
    ADD CONSTRAINT link_pkey PRIMARY KEY (id);
 ;   ALTER TABLE ONLY elearning.link DROP CONSTRAINT link_pkey;
    	   elearning            postgres    false    212            �           2606    24772 &   login_activities login_activities_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY elearning.login_activities
    ADD CONSTRAINT login_activities_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY elearning.login_activities DROP CONSTRAINT login_activities_pkey;
    	   elearning            postgres    false    214            �           2606    24797     main_category main_category_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY elearning.main_category
    ADD CONSTRAINT main_category_pkey PRIMARY KEY (id);
 M   ALTER TABLE ONLY elearning.main_category DROP CONSTRAINT main_category_pkey;
    	   elearning            postgres    false    222            �           2606    24828    state state_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY elearning.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY elearning.state DROP CONSTRAINT state_pkey;
    	   elearning            postgres    false    228            �           2606    24816    sub_category sub_category_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY elearning.sub_category
    ADD CONSTRAINT sub_category_pkey PRIMARY KEY (id);
 K   ALTER TABLE ONLY elearning.sub_category DROP CONSTRAINT sub_category_pkey;
    	   elearning            postgres    false    226            �           2606    24774 "   task_allotment task_allotment_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY elearning.task_allotment
    ADD CONSTRAINT task_allotment_pkey PRIMARY KEY (id);
 O   ALTER TABLE ONLY elearning.task_allotment DROP CONSTRAINT task_allotment_pkey;
    	   elearning            postgres    false    216            �           2606    24776    tasks tasks_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY elearning.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (task_id);
 =   ALTER TABLE ONLY elearning.tasks DROP CONSTRAINT tasks_pkey;
    	   elearning            postgres    false    218            �           2606    24866    translation translation_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY elearning.translation
    ADD CONSTRAINT translation_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY elearning.translation DROP CONSTRAINT translation_pkey;
    	   elearning            postgres    false    234            �           2606    24778 &   user_information user_information_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY elearning.user_information
    ADD CONSTRAINT user_information_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY elearning.user_information DROP CONSTRAINT user_information_pkey;
    	   elearning            postgres    false    219            �           2606    24780    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY elearning.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY elearning.users DROP CONSTRAINT users_pkey;
    	   elearning            postgres    false    220            �           2606    24805 $   category fk1o9opwme2q425qr8iwr3jqqml    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.category
    ADD CONSTRAINT fk1o9opwme2q425qr8iwr3jqqml FOREIGN KEY (main_category_id) REFERENCES elearning.main_category(id);
 Q   ALTER TABLE ONLY elearning.category DROP CONSTRAINT fk1o9opwme2q425qr8iwr3jqqml;
    	   elearning          postgres    false    3252    222    224            �           2606    24877 '   translation fk6ydih7d505b8st2okxk1xabig    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.translation
    ADD CONSTRAINT fk6ydih7d505b8st2okxk1xabig FOREIGN KEY (user_id) REFERENCES elearning.users(id);
 T   ALTER TABLE ONLY elearning.translation DROP CONSTRAINT fk6ydih7d505b8st2okxk1xabig;
    	   elearning          postgres    false    220    234    3250            �           2606    24781 '   audit_table fkba7ryvuqmap66o2e5nm93d0ct    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.audit_table
    ADD CONSTRAINT fkba7ryvuqmap66o2e5nm93d0ct FOREIGN KEY (id) REFERENCES elearning.login_activities(id);
 T   ALTER TABLE ONLY elearning.audit_table DROP CONSTRAINT fkba7ryvuqmap66o2e5nm93d0ct;
    	   elearning          postgres    false    214    3242    210            �           2606    24872 '   translation fkc2d46w0v0yhqdf6nyel7g0b3u    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.translation
    ADD CONSTRAINT fkc2d46w0v0yhqdf6nyel7g0b3u FOREIGN KEY (language_id) REFERENCES elearning.language(id);
 T   ALTER TABLE ONLY elearning.translation DROP CONSTRAINT fkc2d46w0v0yhqdf6nyel7g0b3u;
    	   elearning          postgres    false    230    3260    234            �           2606    24853 3   english_words_sentences fkfmlo13gpo8avdcbf5ev3sa6fu    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.english_words_sentences
    ADD CONSTRAINT fkfmlo13gpo8avdcbf5ev3sa6fu FOREIGN KEY (sub_category_id) REFERENCES elearning.sub_category(id);
 `   ALTER TABLE ONLY elearning.english_words_sentences DROP CONSTRAINT fkfmlo13gpo8avdcbf5ev3sa6fu;
    	   elearning          postgres    false    3256    232    226            �           2606    24867 '   translation fkjybo73axn5650c0lm81nk9lhn    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.translation
    ADD CONSTRAINT fkjybo73axn5650c0lm81nk9lhn FOREIGN KEY (eng_word_id) REFERENCES elearning.english_words_sentences(id);
 T   ALTER TABLE ONLY elearning.translation DROP CONSTRAINT fkjybo73axn5650c0lm81nk9lhn;
    	   elearning          postgres    false    232    234    3262            �           2606    24817 (   sub_category fkl65dyy5me2ypoyj8ou1hnt64e    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.sub_category
    ADD CONSTRAINT fkl65dyy5me2ypoyj8ou1hnt64e FOREIGN KEY (category_id) REFERENCES elearning.category(id);
 U   ALTER TABLE ONLY elearning.sub_category DROP CONSTRAINT fkl65dyy5me2ypoyj8ou1hnt64e;
    	   elearning          postgres    false    224    3254    226            �           2606    24848 3   english_words_sentences fkndeeb0rmna94drfkslsoq68ql    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.english_words_sentences
    ADD CONSTRAINT fkndeeb0rmna94drfkslsoq68ql FOREIGN KEY (category_id) REFERENCES elearning.category(id);
 `   ALTER TABLE ONLY elearning.english_words_sentences DROP CONSTRAINT fkndeeb0rmna94drfkslsoq68ql;
    	   elearning          postgres    false    232    3254    224            �           2606    24836 $   language fknup8bu29qvhdi4k670fugh15f    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.language
    ADD CONSTRAINT fknup8bu29qvhdi4k670fugh15f FOREIGN KEY (state_id) REFERENCES elearning.state(id);
 Q   ALTER TABLE ONLY elearning.language DROP CONSTRAINT fknup8bu29qvhdi4k670fugh15f;
    	   elearning          postgres    false    228    230    3258            �           2606    24786 *   task_allotment fkogbnye7nb3lpw3m64u14wdyai    FK CONSTRAINT     �   ALTER TABLE ONLY elearning.task_allotment
    ADD CONSTRAINT fkogbnye7nb3lpw3m64u14wdyai FOREIGN KEY (task_id) REFERENCES elearning.tasks(task_id);
 W   ALTER TABLE ONLY elearning.task_allotment DROP CONSTRAINT fkogbnye7nb3lpw3m64u14wdyai;
    	   elearning          postgres    false    3246    218    216            V   I   x�3�LLI�434��4N2�5ML4�5I2Jҵ413�MM66�L250531�-N-*��2"M�g^Z>PW� �U�      d   �   x���
�0�ϛ��'�G+� ���em�&�lJ�}{o3��1+8��k�~�F!E��.V��&�,�sxFX�-�77�N\��uC�H��ї���ڃfI,G�/�EC�I:rWs�R��$�^\V��c�����.w      l      x������ � �      j      x������ � �      X      x������ � �      Z   �   x��ӻmA���
5���].kQr���X8	2~�����8�a��V�S�ď�#����k�:�[�p�^��.�Sj�S� ��	� }�	1�h(���d�=�KP4UiO��Y�ƩAb��|���Q�3��w3�]���)�	�K�9]Ȭ��-_ͩ��V���V�6cA5��bi�4��y�y�?��&      b   9   x�3�v�u��tVpvqu���2�tr��u
:� ���J�B\��]��b���� ���      h      x������ � �      f   �   x�%��
�0D�7_qW.���]M[,h"mD7�\h�H�¿7��a�S@i=36��������!��)�
8���#��r��>D��g��P���\�R��m���{���7hr�|Ǟ�<���NO�y2�����zLc_�[)�      \      x������ � �      ^      x������ � �      n      x������ � �      _   �   x�5�=�0@��90J�8qF�����!�G���0��o{�D�KCu-�W!��꠽�Ͳ��$�{rp�̹�ys~<�����	�w��Ȫ}u���(U�|�$��T�c�MS.0��s�c>��!�      `   
  x�]�Mo�@�3�
\��ś�j�R�j�&ͬ@m� j�ח�4s��;ɛGJ	�
���T" P��2b5C1�`DĀx#�F\ui������q_�`'���u8�4Y?-�c��y0\��"o.x���Q��f�ڍ�ޮ�L���j�?�Y�W�(�_$��H H�5���rǘo�{��r@�w\�b��]�n�u�٩���S�����_�)�4����B,�`X�.�<8��o�,���ٴw�ľ�~wm��z]     