ALTER TABLE evaluaciones ADD COLUMN video_id BIGINT NOT NULL;
ALTER TABLE evaluaciones ADD CONSTRAINT fk_video FOREIGN KEY (video_id) REFERENCES video(id);
