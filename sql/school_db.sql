-- Créer la base de données si elle n'existe pas déjà
CREATE DATABASE IF NOT EXISTS school_db;

-- Sélectionner la base de données
USE school_db;

-- Créer la table 'students'
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    age INT
);

-- Créer la table 'teachers'
CREATE TABLE IF NOT EXISTS teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

-- Créer la table 'courses'
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255)
);
