class Komputer {
    // To do: Buatlah 3 variable sesuai ketentuan
    protected int JumlahKomputer;
    protected String namaWarnet;
    protected float hargaPerjam;
    
    // To do: Buatlah constructor pada class Komputer
    public Komputer (int JumlahKomputer, String namaWarnet, float hargaPerjam) {
        this.JumlahKomputer = JumlahKomputer;
        this.namaWarnet = namaWarnet;
        this.hargaPerjam = hargaPerjam;
    }
    
    // To do: Buatlah Method Informasi dengan isi yang sesuai dengan ketentuan 
    public void Informasi() {
        System.out.println(JumlahKomputer);
        System.out.println(namaWarnet);
        System.out.println(hargaPerjam);
    }
    // (boleh berbeda dengan output jurnal tetapi dengan cangkupan masih sesuai oleh materi modul!)

}