CREATE TABLE mentoria_video(
    mentoria_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,

    PRIMARY KEY (mentoria_id, video_id),
    FOREIGN KEY (mentoria_id) REFERENCES mentorias(id),
    FOREIGN KEY (video_id) REFERENCES video(id)
);