package zona_fit.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private int id_client;
    private String name_client;
    private String last_name_client;
    private String email_client;
    private int membership_number;
    private Date date_registered;

    public Client(){}

    public Client(int id_client){
        this.id_client= id_client;
    }

    public Client(String name_client, String last_name_client, String email_client, int membership_number){
        this.name_client = name_client;
        this.last_name_client = last_name_client;
        this.membership_number = membership_number;
    }

    public Client(int id_client, String name_client, String last_name_client, String email_client, int membership_number,
            Date date_registered) {
        this(name_client, last_name_client, email_client, membership_number);
        this.id_client = id_client;
        this.date_registered = date_registered;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getLast_name_client() {
        return last_name_client;
    }

    public void setLast_name_client(String last_name_client) {
        this.last_name_client = last_name_client;
    }

    public String getEmail_client() {
        return email_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public int getMembership_number() {
        return membership_number;
    }

    public void setMembership_number(int membership_number) {
        this.membership_number = membership_number;
    }

    public Date getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(Date date_registered) {
        this.date_registered = date_registered;
    }

    @Override
    public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = (date_registered != null) ? sdf.format(date_registered) : "N/A";
    
    StringBuilder sb = new StringBuilder();
    sb.append("Client{");
    sb.append("id_client= ").append(id_client);
    sb.append(", name_client= '").append(name_client).append('\'');
    sb.append(", last_name_client= '").append(last_name_client).append('\'');
    sb.append("email_client= ").append(email_client);
    sb.append(", membership_number= ").append(membership_number);
    sb.append(", date_registered= '").append(formattedDate).append('\'');
    sb.append('}');
    return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_client;
        result = prime * result + ((name_client == null) ? 0 : name_client.hashCode());
        result = prime * result + ((last_name_client == null) ? 0 : last_name_client.hashCode());
        result = prime * result + ((email_client == null) ? 0 : email_client.hashCode());
        result = prime * result + membership_number;
        result = prime * result + ((date_registered == null) ? 0 : date_registered.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id_client != other.id_client)
            return false;
        if (name_client == null) {
            if (other.name_client != null)
                return false;
        } else if (!name_client.equals(other.name_client))
            return false;
        if (last_name_client == null) {
            if (other.last_name_client != null)
                return false;
        } else if (!last_name_client.equals(other.last_name_client))
            return false;
        if (email_client == null) {
            if (other.email_client != null)
                return false;
        } else if (!email_client.equals(other.email_client))
            return false;
        if (membership_number != other.membership_number)
            return false;
        if (date_registered == null) {
            if (other.date_registered != null)
                return false;
        } else if (!date_registered.equals(other.date_registered))
            return false;
        return true;
    }
}
