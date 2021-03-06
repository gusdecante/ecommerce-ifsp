package dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;

import org.bson.types.ObjectId;

import model.SalePFisica;
import util.MongoConnection;

public class SalePFisicaDao {
    private MongoDatabase dtbase;

    public SalePFisicaDao() {
        this.dtbase = new MongoConnection().getDatabase();
    }

    public int registerSalePFisica(SalePFisica sl) {
        int ok = 0;
        try {
            MongoCollection<Document> docs = dtbase.getCollection("salePFisica");

            Document rDoc = new Document();
            // Fields Pessoa_fisica
            rDoc.append("idPessoaFisica", sl.getIdPessoaFisica());
            rDoc.append("nameCustomer", sl.getNameCustomer());
            rDoc.append("CPF", sl.getCPF());
            rDoc.append("RG", sl.getRG());
            rDoc.append("dateBith", sl.getDateBirth());

            // Fields user
            rDoc.append("email", sl.getEmail());

            // Fields Address
            rDoc.append("street", sl.getStreet());
            rDoc.append("number", sl.getNumber());
            rDoc.append("district", sl.getDistrict());
            rDoc.append("city", sl.getCity());
            rDoc.append("state", sl.getState());
            rDoc.append("zipCode", sl.getZipCode());

            // Fields Phone
            rDoc.append("phone", sl.getPhone());

            // Fields order
            rDoc.append("date", sl.getDate());

            // Fields Payment_Form
            rDoc.append("paymentForm", sl.getPaymentForm());

            // Fields Product
            rDoc.append("color", sl.getColor());
            rDoc.append("finishingProcess", sl.getFinishingProcess());
            rDoc.append("cubaType", sl.getCubaType());
            rDoc.append("description", sl.getDescription());
            rDoc.append("imageLink", sl.getImageLink());
            rDoc.append("unitaryValue", sl.getUnitaryValue());

            // Fields Order Item
            rDoc.append("amount", sl.getAmount());

            // Fields Category
            rDoc.append("category", sl.getCategory());

            if (sl.getColor2() == null || sl.getColor2().equals("")) {

            } else {
                // Fields Product
                rDoc.append("color2", sl.getColor2());
                rDoc.append("finishingProcess2", sl.getFinishingProcess2());
                rDoc.append("cubaType2", sl.getCubaType2());
                rDoc.append("description2", sl.getDescription2());
                rDoc.append("imageLink2", sl.getImageLink2());
                rDoc.append("unitaryValue2", sl.getUnitaryValue2());

                // Fields Order Item
                rDoc.append("amount2", sl.getAmount2());

                // Fields Category
                rDoc.append("category2", sl.getCategory2());
            }

            if (sl.getColor3() == null || sl.getColor3().equals("")) {

            } else {
                // Fields Product
                rDoc.append("color3", sl.getColor3());
                rDoc.append("finishingProcess3", sl.getFinishingProcess3());
                rDoc.append("cubaType3", sl.getCubaType3());
                rDoc.append("description3", sl.getDescription3());
                rDoc.append("imageLink3", sl.getImageLink3());
                rDoc.append("unitaryValue3", sl.getUnitaryValue3());

                // Fields Order Item
                rDoc.append("amount3", sl.getAmount3());

                // Fields Category
                rDoc.append("category3", sl.getCategory());
            }

            // Field SalePFisica
            rDoc.append("total", sl.getTotal());

            docs.insertOne(rDoc);
            ok = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok;

    }

    public String searchSalePFisica() {
        MongoCollection<Document> collection = dtbase.getCollection("salePFisica");
        MongoCursor<Document> sSale = collection.find().iterator();

        List<String> lstSale = new ArrayList<String>();

        while (sSale.hasNext()) {
            Document sDoc = sSale.next();
            lstSale.add(sDoc.toJson());
        }

        return lstSale.toString();
    }

    public String searchSalePFisica(int pFisica) {
        MongoCollection<Document> collection = dtbase.getCollection("salePFisica");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("idPessoaFisica", pFisica);
        MongoCursor<Document> sSale = collection.find(searchQuery).iterator();

        List<String> lstSale = new ArrayList<String>();

        while (sSale.hasNext()) {
            Document sDoc = sSale.next();
            lstSale.add(sDoc.toJson());
        }

        return lstSale.toString();
    }

    public String searchSalePFisicaIdMongo(String strParameter) {

        MongoCollection<Document> collection = dtbase.getCollection("salePFisica");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(strParameter));
        MongoCursor<Document> sSale = collection.find(searchQuery).iterator();

        List<String> lstSale = new ArrayList<String>();

        while (sSale.hasNext()) {
            Document sDoc = sSale.next();
            lstSale.add(sDoc.toJson());
        }

        return lstSale.toString();
    }

    public boolean DeleteSalePFisicaIdMongo(String strParameter) {

        MongoCollection<Document> collection = dtbase.getCollection("salePFisica");
        BasicDBObject deleteQuery = new BasicDBObject();
        deleteQuery.put("_id", new ObjectId(strParameter));
        DeleteResult ok = collection.deleteOne(deleteQuery);        
        
        return ok.getDeletedCount() == 1;
    }
}