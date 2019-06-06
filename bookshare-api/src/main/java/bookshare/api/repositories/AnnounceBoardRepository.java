package bookshare.api.repositories;

import bookshare.api.entities.AnnounceBoardEntity;

public interface AnnounceBoardRepository  {
    AnnounceBoardEntity insert(AnnounceBoardEntity announceBoard) throws ClassNotFoundException, Exception;
}
