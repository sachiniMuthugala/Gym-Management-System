package sample;

public interface GymManager {
    public void AddMember(DefaultMember dm);

    public void DeleteMember(int membershipNumber);

    public void PrintMemberList();

    public void SortListNameAsc();

    public void SaveToFile() throws Exception;

    public void OpenGUI() throws Exception;
}
