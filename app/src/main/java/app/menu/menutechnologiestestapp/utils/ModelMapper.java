package app.menu.menutechnologiestestapp.utils;

public interface ModelMapper<Dto, Model> {

    Model convertDtoToModel(Dto dto);
}
