package app.menu.menutechnologiestestapp.domain.mapper;

import app.menu.menutechnologiestestapp.data.dto.AccessTokenDto;
import app.menu.menutechnologiestestapp.domain.model.AccessToken;
import app.menu.menutechnologiestestapp.utils.ModelMapper;

public class AccessTokenMapper implements ModelMapper<AccessTokenDto, AccessToken> {

    @Override
    public AccessToken convertDtoToModel(AccessTokenDto accessTokenDto) {
        return new AccessToken(accessTokenDto.getToken(), accessTokenDto.getAccessTokenValidTo(), accessTokenDto.getRefreshTokenValidTo());
    }
}
