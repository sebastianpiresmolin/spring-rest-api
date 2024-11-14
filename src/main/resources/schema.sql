CREATE TABLE IF NOT EXISTS category(
  id INT AUTO_INCREMENT
      PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  symbol VARCHAR(255)
      CHARACTER SET utf8mb4
          COLLATE utf8mb4_unicode_ci NOT NULL,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS location(
    id INT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id INT,
        FOREIGN KEY (category_id)
            REFERENCES category(id),
    user_id VARCHAR(255) NOT NULL,
    is_private BOOLEAN NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    description VARCHAR(255),
    longitude INT NOT NULL,
    latitude INT NOT NULL,
    deleted BOOLEAN NOT NULL
);
