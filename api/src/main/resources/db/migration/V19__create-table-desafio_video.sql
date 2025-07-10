CREATE TABLE desafio_video(
    desafio_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,

    PRIMARY KEY(desafio_id, video_id),
    FOREIGN KEY (desafio_id) REFERENCES desafios(id),
    FOREIGN KEY (video_id) REFERENCES video(id)
);