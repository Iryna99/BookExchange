package bookshare.api.repositories;

import java.util.List;
import java.sql.SQLException;
import bookshare.api.entities.AnnounceBoardEntity;
import bookshare.api.models.AnnounceDataResponse;

public interface AnnounceBoardRepository  {
    AnnounceBoardEntity insert(AnnounceBoardEntity announceBoard) throws ClassNotFoundException, Exception;
}
