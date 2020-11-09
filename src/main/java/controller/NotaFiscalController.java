package controller;

import DAO.NotaFiscalDAO;
import java.util.ArrayList;
import java.util.Date;
import model.NotaFiscal;

public class NotaFiscalController {

    public static boolean salvar(int pNumeroNota, double pValorNota, Date pData) {

        NotaFiscal obj = new NotaFiscal();
        obj.setNumeroNota(pNumeroNota);
        obj.setValorNota(pValorNota);
        obj.setData(pData);

        return NotaFiscalDAO.salvar(obj);

    }

    public static String[] consultarPorID(int pIDNotaFiscal) {

        NotaFiscal obj = NotaFiscalDAO.consultaPorId(pIDNotaFiscal);
        String[] retorno = null;

        if (obj != null) {
            retorno = new String[]{String.valueOf(obj.getIdNotaFiscal()),
                String.valueOf(obj.getNumeroNota()),
                String.valueOf(obj.getValorNota()),
                String.valueOf(obj.getData())};
        }
        return retorno;
    }

    public static ArrayList<String[]> listar() {
        //TODO: Chamar a DAO para consultar todas as notas
        ArrayList<NotaFiscal> listaNotas = NotaFiscalDAO.listar();
        ArrayList<String[]> retorno = new ArrayList<>();

        for (NotaFiscal nota : listaNotas) {
            retorno.add(new String[]{String.valueOf(nota.getIdNotaFiscal()),
                String.valueOf(nota.getNumeroNota()),
                String.valueOf(nota.getValorNota()),
                String.valueOf(nota.getData())});

        }
        return retorno;
    }
    
        public static ArrayList<String[]> listarPorData(Date pInicio, Date pFim) {
        //TODO: Chamar a DAO para consultar todas as notas
        ArrayList<NotaFiscal> listaNotas = NotaFiscalDAO.listarPorData(pInicio, pFim);
        ArrayList<String[]> retorno = new ArrayList<>();

        for (NotaFiscal nota : listaNotas) {
            retorno.add(new String[]{String.valueOf(nota.getIdNotaFiscal()),
                String.valueOf(nota.getNumeroNota()),
                String.valueOf(nota.getValorNota()),
                String.valueOf(nota.getData())});

        }
        return retorno;
    }

    public static boolean atualizar(int pIDNotaFiscal, int pNumeroNota, double pValorNota) {

        //TODO: chamar a DAO
        NotaFiscal objAtualizar = new NotaFiscal();
        objAtualizar.setIdNotaFiscal(pIDNotaFiscal);
        objAtualizar.setNumeroNota(pNumeroNota);
        objAtualizar.setValorNota(pValorNota);

        return NotaFiscalDAO.atualizar(objAtualizar);

    }

    public static boolean excluir(int pIDNotaFiscal){
    
        return NotaFiscalDAO.excluir(pIDNotaFiscal);

    }

}
