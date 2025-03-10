PGDMP      :                 }            postgres    17.0    17.0 ,    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    5    postgres    DATABASE     {   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE postgres;
                     postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                        postgres    false    4842            �            1259    16663    eskaera_xehetasunak    TABLE     �   CREATE TABLE public.eskaera_xehetasunak (
    id_xehetasuna integer NOT NULL,
    id_eskaera integer,
    id_produktua integer,
    kantitatea integer NOT NULL,
    azpitotala numeric(10,2) NOT NULL
);
 '   DROP TABLE public.eskaera_xehetasunak;
       public         heap r       postgres    false            �            1259    16662 %   eskaera_xehetasunak_id_xehetasuna_seq    SEQUENCE     �   CREATE SEQUENCE public.eskaera_xehetasunak_id_xehetasuna_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.eskaera_xehetasunak_id_xehetasuna_seq;
       public               postgres    false    222            �           0    0 %   eskaera_xehetasunak_id_xehetasuna_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.eskaera_xehetasunak_id_xehetasuna_seq OWNED BY public.eskaera_xehetasunak.id_xehetasuna;
          public               postgres    false    221            �            1259    16655    eskaerak    TABLE     �   CREATE TABLE public.eskaerak (
    id_eskaera integer NOT NULL,
    eskaera_data timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    guztira numeric(10,2) NOT NULL
);
    DROP TABLE public.eskaerak;
       public         heap r       postgres    false            �            1259    16654    eskaerak_id_eskaera_seq    SEQUENCE     �   CREATE SEQUENCE public.eskaerak_id_eskaera_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.eskaerak_id_eskaera_seq;
       public               postgres    false    220            �           0    0    eskaerak_id_eskaera_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.eskaerak_id_eskaera_seq OWNED BY public.eskaerak.id_eskaera;
          public               postgres    false    219            �            1259    16636 
   kategoriak    TABLE     z   CREATE TABLE public.kategoriak (
    id_kategoria integer NOT NULL,
    kategoria_izena character varying(50) NOT NULL
);
    DROP TABLE public.kategoriak;
       public         heap r       postgres    false            �            1259    16635    kategoriak_id_kategoria_seq    SEQUENCE     �   CREATE SEQUENCE public.kategoriak_id_kategoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.kategoriak_id_kategoria_seq;
       public               postgres    false    218            �           0    0    kategoriak_id_kategoria_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.kategoriak_id_kategoria_seq OWNED BY public.kategoriak.id_kategoria;
          public               postgres    false    217            �            1259    16728 
   produktuak    TABLE     �   CREATE TABLE public.produktuak (
    id_produktua integer NOT NULL,
    produktua_izena character varying(100) NOT NULL,
    prezioa double precision NOT NULL,
    id_kategoria integer
);
    DROP TABLE public.produktuak;
       public         heap r       postgres    false            �            1259    16727    produktuak_id_produktua_seq    SEQUENCE     �   CREATE SEQUENCE public.produktuak_id_produktua_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.produktuak_id_produktua_seq;
       public               postgres    false    224            �           0    0    produktuak_id_produktua_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.produktuak_id_produktua_seq OWNED BY public.produktuak.id_produktua;
          public               postgres    false    223            �            1259    24769    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    24768    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public               postgres    false    226            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public               postgres    false    225            8           2604    16666 !   eskaera_xehetasunak id_xehetasuna    DEFAULT     �   ALTER TABLE ONLY public.eskaera_xehetasunak ALTER COLUMN id_xehetasuna SET DEFAULT nextval('public.eskaera_xehetasunak_id_xehetasuna_seq'::regclass);
 P   ALTER TABLE public.eskaera_xehetasunak ALTER COLUMN id_xehetasuna DROP DEFAULT;
       public               postgres    false    221    222    222            6           2604    16658    eskaerak id_eskaera    DEFAULT     z   ALTER TABLE ONLY public.eskaerak ALTER COLUMN id_eskaera SET DEFAULT nextval('public.eskaerak_id_eskaera_seq'::regclass);
 B   ALTER TABLE public.eskaerak ALTER COLUMN id_eskaera DROP DEFAULT;
       public               postgres    false    219    220    220            5           2604    16639    kategoriak id_kategoria    DEFAULT     �   ALTER TABLE ONLY public.kategoriak ALTER COLUMN id_kategoria SET DEFAULT nextval('public.kategoriak_id_kategoria_seq'::regclass);
 F   ALTER TABLE public.kategoriak ALTER COLUMN id_kategoria DROP DEFAULT;
       public               postgres    false    217    218    218            9           2604    16731    produktuak id_produktua    DEFAULT     �   ALTER TABLE ONLY public.produktuak ALTER COLUMN id_produktua SET DEFAULT nextval('public.produktuak_id_produktua_seq'::regclass);
 F   ALTER TABLE public.produktuak ALTER COLUMN id_produktua DROP DEFAULT;
       public               postgres    false    223    224    224            :           2604    24772    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    225    226    226            �          0    16663    eskaera_xehetasunak 
   TABLE DATA           n   COPY public.eskaera_xehetasunak (id_xehetasuna, id_eskaera, id_produktua, kantitatea, azpitotala) FROM stdin;
    public               postgres    false    222   �3       �          0    16655    eskaerak 
   TABLE DATA           E   COPY public.eskaerak (id_eskaera, eskaera_data, guztira) FROM stdin;
    public               postgres    false    220   �5       �          0    16636 
   kategoriak 
   TABLE DATA           C   COPY public.kategoriak (id_kategoria, kategoria_izena) FROM stdin;
    public               postgres    false    218   �7       �          0    16728 
   produktuak 
   TABLE DATA           Z   COPY public.produktuak (id_produktua, produktua_izena, prezioa, id_kategoria) FROM stdin;
    public               postgres    false    224   �7       �          0    24769    users 
   TABLE DATA           7   COPY public.users (id, username, password) FROM stdin;
    public               postgres    false    226   �9       �           0    0 %   eskaera_xehetasunak_id_xehetasuna_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.eskaera_xehetasunak_id_xehetasuna_seq', 65, true);
          public               postgres    false    221            �           0    0    eskaerak_id_eskaera_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.eskaerak_id_eskaera_seq', 46, true);
          public               postgres    false    219            �           0    0    kategoriak_id_kategoria_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.kategoriak_id_kategoria_seq', 8, true);
          public               postgres    false    217            �           0    0    produktuak_id_produktua_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.produktuak_id_produktua_seq', 32, true);
          public               postgres    false    223            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 34, true);
          public               postgres    false    225            @           2606    16668 ,   eskaera_xehetasunak eskaera_xehetasunak_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.eskaera_xehetasunak
    ADD CONSTRAINT eskaera_xehetasunak_pkey PRIMARY KEY (id_xehetasuna);
 V   ALTER TABLE ONLY public.eskaera_xehetasunak DROP CONSTRAINT eskaera_xehetasunak_pkey;
       public                 postgres    false    222            >           2606    16661    eskaerak eskaerak_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.eskaerak
    ADD CONSTRAINT eskaerak_pkey PRIMARY KEY (id_eskaera);
 @   ALTER TABLE ONLY public.eskaerak DROP CONSTRAINT eskaerak_pkey;
       public                 postgres    false    220            <           2606    16641    kategoriak kategoriak_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.kategoriak
    ADD CONSTRAINT kategoriak_pkey PRIMARY KEY (id_kategoria);
 D   ALTER TABLE ONLY public.kategoriak DROP CONSTRAINT kategoriak_pkey;
       public                 postgres    false    218            B           2606    16733    produktuak produktuak_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.produktuak
    ADD CONSTRAINT produktuak_pkey PRIMARY KEY (id_produktua);
 D   ALTER TABLE ONLY public.produktuak DROP CONSTRAINT produktuak_pkey;
       public                 postgres    false    224            D           2606    24774    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    226            F           2606    24776    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public                 postgres    false    226            G           2606    16669 7   eskaera_xehetasunak eskaera_xehetasunak_id_eskaera_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.eskaera_xehetasunak
    ADD CONSTRAINT eskaera_xehetasunak_id_eskaera_fkey FOREIGN KEY (id_eskaera) REFERENCES public.eskaerak(id_eskaera);
 a   ALTER TABLE ONLY public.eskaera_xehetasunak DROP CONSTRAINT eskaera_xehetasunak_id_eskaera_fkey;
       public               postgres    false    222    4670    220            H           2606    24756 4   eskaera_xehetasunak eskaera_xehetasunak_id_prod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.eskaera_xehetasunak
    ADD CONSTRAINT eskaera_xehetasunak_id_prod_fkey FOREIGN KEY (id_produktua) REFERENCES public.produktuak(id_produktua) NOT VALID;
 ^   ALTER TABLE ONLY public.eskaera_xehetasunak DROP CONSTRAINT eskaera_xehetasunak_id_prod_fkey;
       public               postgres    false    224    222    4674            I           2606    16734 '   produktuak produktuak_id_kategoria_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.produktuak
    ADD CONSTRAINT produktuak_id_kategoria_fkey FOREIGN KEY (id_kategoria) REFERENCES public.kategoriak(id_kategoria);
 Q   ALTER TABLE ONLY public.produktuak DROP CONSTRAINT produktuak_id_kategoria_fkey;
       public               postgres    false    4668    218    224            �   �  x�USɕ�0;�bx񒭉���_�H�!9�d%����ML��R�p/�.��� �AX�g�j?�m��Q���Q�g+�X�.N	�D��S˂.qV@Ƞ�w�&�9��D?>�c,�IKHf[�)Ji�O��"P�(g��@�����n�"��a��P�&��1��<1$�	�R>'�P#����7f4��c��7�^�IzD��~���{07�Ǝ�JM��F
�푝�a����@�ޏ.ss�z"o'�
+��{hq�{�g;)M�����%5szD�%�\f�t>�'���h���k!n|z�N����X��U�0��p$�x�KVX��kw���xi	��NB����"��~��^�!6��k6�� ��2����C�w�����lo�w����      �      x�m�ّ"1 �o	��}8��?��ݰ0f~�^Yw�`d}?��Sc� ��ס	�7>��$��A���D�T�y�E�M�9	� T��X��M�5Y6��@�F�_O�A;_z',t��l�2d K�m�4>E������H�U��A��$l��D���e�4:�#,��îPq�IJ3܇^�ilAdVW�{T�Q\�(���0���A MtP��a{TL'�)	�����6|�^C@+�6{X,�1���֥�ؖ����rK�rŲ�hW��a��
�'��.Ց��_�����Y"î��ib�EvwV5���OĞ(zѰK�4��=�Bz�G�4�@T3y�lÇ���ְ�a"��)�n�Xz��6�M�Y�طY���?���f�E���3�T�n�� ���G��K@�th螺�'Y�ӧ����b�R<��� ��Q��~���ԫ�νק��Q��ʿ̮(zHy	�=�N�*_F�0�=hb�2��I�����o�����VO����@cl      �   V   x�3�t+J-�NLO,*�L��2�t,J�2�9��@�`�	gTiv)�a�靘�
d�q�e�9}2��@����yI�y�% �1z\\\ %��      �   �  x�]�͎�0��������F+tu��e��6^�L�8�N��c�t����o��N5[��d�ܤ�xb9|�A{w�n�Ť;�=�U%<���=:r��E)�
�L��)/3p7�@^ڿG﬛s���ηx:�<iB��K��{"�CdX.V%*��t��Ȑ-�WpO�-��ﴯ�5|v�_8�|�m^(��-FװnHۖ����-�������1��d[�AJe�W!b\&����n�D@������'�T���@?�8"�P)S��(��I�`�,�i!|���J�5ԁN��0>�S�o������k$_Bݎ��<ȩ���QJX��\�~���F�.��G�o�ޱ�T�Q2O��o8�b�jwD/�.�l�Ӎʖ��Im�;�~$�<~ǯ�ݿ<O΅�Ϯ��ї�R�/t�      �   ]   x�34���,.I��442�24���)�L�93�S� l#�:�Bǔ��<��%�'L�� ��h4�p��cǘ�lT�	X�̉���� Ko"�     