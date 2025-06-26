ALTER TABLE video ADD COLUMN uploader_id BIGINT NOT NULL;
ALTER TABLE video ADD CONSTRAINT fk_uploader FOREIGN KEY (uploader_id) REFERENCES usuarios(id);
