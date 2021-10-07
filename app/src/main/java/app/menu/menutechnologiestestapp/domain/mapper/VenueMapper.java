package app.menu.menutechnologiestestapp.domain.mapper;

import app.menu.menutechnologiestestapp.data.dto.VenueObjectDto;
import app.menu.menutechnologiestestapp.domain.model.Venue;
import app.menu.menutechnologiestestapp.utils.ModelMapper;

public class VenueMapper implements ModelMapper<VenueObjectDto, Venue> {
    @Override
    public Venue convertDtoToModel(VenueObjectDto venueObjectDto) {
        if (venueObjectDto.getImageDto() != null) {
            return new Venue(venueObjectDto.getName(), venueObjectDto.getDescription(), venueObjectDto.isOpen(),
                    venueObjectDto.getWelcome_message(), venueObjectDto.getImageDto().getThumbnailMediumUrl());
        }
        else {
            return new Venue(venueObjectDto.getName(), venueObjectDto.getDescription(), venueObjectDto.isOpen(),
                    venueObjectDto.getWelcome_message());
        }
    }
}
