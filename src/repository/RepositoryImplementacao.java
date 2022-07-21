package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RepositoryImplementacao<I, T> implements Repository<I,T> {

    Map<I, T> repository = new TreeMap<>();

    @Override
    public List buscarTodos() {
        return repository.values().stream().collect(Collectors.toList());
    }

    @Override
    public T buscarPorId(I id) {
        return repository.get(id);
    }

    @Override
    public void salvar(I id, T objeto) {
        repository.put(id, objeto);
    }

    @Override
    public void excluir(I id) {
        repository.remove(id);

    }
}
