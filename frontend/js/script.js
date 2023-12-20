
const orderStatus = {
  0: "Создан",
  1: "Принят в работу",
  2: "Отправлен",
  3: "Доставлен",
  4: "Принят"
}

const shipmentsStatus = {
  0: "Создан",
  1: "В доставке",
  2: "Доставлен",
  3: "Принят"
}



const tableBody = document.getElementById('ordersTableBody');
const btn = document.querySelector('.orderBtn');

if (btn) {
  // Заказы
btn.addEventListener('click', function(e) {

  e.preventDefault();
  tableBody.innerHTML = '';
  fetch('http://localhost:8080/orders')
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(jsonData => {
    jsonData.forEach(order => {
      const row = document.createElement('tr');
    
      const orderId = document.createElement('th');
      orderId.textContent = order.orderId;
      orderId.classList.add("orderTd")
      row.appendChild(orderId);
    
      const orderDate = document.createElement('th');
      orderDate.textContent = order.orderDate;
      
      row.appendChild(orderDate);
    
      const status = document.createElement('th');
      status.textContent = orderStatus[order.status];
      row.appendChild(status);
    
      const deliveryDate = document.createElement('th');
      deliveryDate.textContent = order.deliveryDate;
      row.appendChild(deliveryDate);
      
      const vehicle = document.createElement('th');
      vehicle.textContent = order.vehicle;
      row.appendChild(vehicle);

      tableBody.appendChild(row);
    });
  })
  .catch(error => {
    // Обработка ошибок
    console.error('There has been a problem with your fetch operation:', error);
  });
 
  console.log(1);

});
}

// Грузы



// нформация по доставк 

const deliveryTable = document.getElementById('deliveredShipmentsTableBody');
const deliveryBtn = document.querySelector(".deliveryBtn")

if (deliveryBtn) {
  deliveryBtn.addEventListener("click", (e) => {
    deliveryTable.innerHTML = '';
    e.preventDefault();
  
    fetch('http://localhost:8080/deliveryInformation')
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(jsonData => {
      
      jsonData.forEach(item => {
        const row = document.createElement('tr');
      
        const timelineId = document.createElement('th');
        timelineId.textContent = item.timelineId;
        row.appendChild(timelineId);
      
        const orderId = document.createElement('th');
        orderId.textContent = item.orderId;
        row.appendChild(orderId);
      
        const plannedDeliveryDate = document.createElement('th');
        plannedDeliveryDate.textContent = item.plannedDeliveryDate;
        row.appendChild(plannedDeliveryDate);
      
        const actualDeliveryDate = document.createElement('th');
        actualDeliveryDate.textContent = item.actualDeliveryDate;
        row.appendChild(actualDeliveryDate);
      
        deliveryTable.appendChild(row);
      });
    })
    .catch(error => {
      // Обработка ошибок
      console.error('There has been a problem with your fetch operation:', error);
    });

    console.log(1);
  });
}

// Доставленные грузы

const deliveredBtn = document.querySelector(".deliveredBtn")
const deliveredShipmentsTable = document.getElementById('deliveredShipmentsTableBody');

if (deliveredBtn) {
  deliveredBtn.addEventListener('click', (e) => {
      deliveredShipmentsTable.innerHTML = '';
      e.preventDefault();

  fetch('http://localhost:8080/deliveredShipments')
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(jsonData => {
      
      jsonData.forEach(item => {
        const row = document.createElement('tr');
  
      
        const shipmentId = document.createElement('th');
        shipmentId.textContent = item.shipmentId;
        row.appendChild(shipmentId);
      
        const deliveryDate = document.createElement('th');
        deliveryDate.textContent = item.deliveryDate;
        row.appendChild(deliveryDate);
      
        const deliveryStatus = document.createElement('th');
        let status;
        if (item.deliveryStatus == 1) {
          status = 'Принят';
        } else {
          status = "Доставлен";
        }
        deliveryStatus.textContent = status;
        row.appendChild(deliveryStatus);
      
        deliveredShipmentsTable.appendChild(row);
      });
    })
    .catch(error => {
      // Обработка ошибок
      console.error('There has been a problem with your fetch operation:', error);
    });
    console.log(1);
  });
}

// Создание заказа 
const orderForm = document.querySelector('.orderForm');

if (orderForm) {
  orderForm.addEventListener('submit', function(event) {
    event.preventDefault();
  
    const orderDate = document.getElementById('orderDate').value;
    const deliveryDate = document.getElementById('deliveryDate').value;
    const selectElement = document.getElementById('vehicle');
    const vehicle = decodeURIComponent(selectElement.options[selectElement.selectedIndex].text);

  
    // console.log(vehicle);

    // const queryParams = new URLSearchParams({
    //   orderDate: orderDate,
    //   status: 0,
    //   deliveryDate: deliveryDate,
    //   vehicle: decodeURIComponent(vehicle)
    // });

    let queryString = `orderDate=${orderDate}&status=0&deliveryDate=${deliveryDate}&vehicle=${vehicle}`;


    console.log(queryString);
  
    fetch('http://localhost:8080/orders?' + queryString, {
      method: 'POST', // Метод POST для отправки данных
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded' // Тип данных
      },
      // Тело запроса, в случае POST это данные формы
      body: queryString
    })
    .then(response => {
      // Обработка успешного ответа сервера
      console.log('Данные успешно отправлены');
    })
    .catch(error => {
      // Обработка ошибки
      console.error('Произошла ошибка:', error);
    });

    console.log(1);
  });
}


const shipmentBtn = document.querySelector('.shipmentBtn');
const shipmentTable = document.getElementById('shipmentsTableBody');
if (shipmentBtn) {

  shipmentBtn.addEventListener('click', (e) => {

        e.preventDefault();
        shipmentTable.innerHTML = '';
        fetch('http://localhost:8080/shipments')
        .then(response => response.json())
        .then(data => {
          console.log("Данные успешно получены")
          
      
          // Проход по каждому элементу данных и создание строк таблицы
          data.forEach(shipment => {
            const row = document.createElement('tr');
            row.innerHTML = `
              <th>${shipment.shipmentId}</th>
              <th>${shipment.shipmentType}</th>
              <th>${shipment.weight}</th>
              <th>${shipment.volume}</th>
              <th>${shipment.orderId}</th>
              <th>${shipmentsStatus[shipment.deliveryStatus]}</th>
            `;
            shipmentTable.appendChild(row);
          });
        })
        .catch(error => console.error('Ошибка получения данных:', error));
    
  });

}

//Получение грузов по филттру
const shipmentsCategories = {
  "Все категории": -1,
  "Электронника": 1,
  "Бытовая техника": 2,
  "Химикаты": 3,
  "Товары для дома": 4,
  "Продукты": 5, 
  "Сад и дом": 6, 
  "Одежда": 7,
  "Обувь": 8
}

console.log(shipmentsCategories["Химикаты"]);

const filterBtn = document.querySelector('.filterBtn');
if (filterBtn) {
  filterBtn.addEventListener('click', function(event) {
    event.preventDefault(); // Отменяем действие по умолчанию (в данном случае - отправку формы)
    shipmentTable.innerHTML = '';


    const category = document.getElementById('categorysFilter').value;

    console.log(category);
    const type = document.getElementById('typeFilter').value;
    let queryString;
    if (type == "Все типы") {
        queryString = `shipmentType&categoryId=${shipmentsCategories[category]}`;
    } else {
      queryString = `shipmentType=${type}&categoryId=${shipmentsCategories[category]}`;
    }

  
    console.log(queryString)
  
    // Отправка POST-запроса с данными из формы
    fetch('http://localhost:8080/shipmentsFilter?' + queryString)
    .then(response => response.json())
    .then(data => {
      console.log("Данные успешно получены")
      console.log(data);
      
  
      // Проход по каждому элементу данных и создание строк таблицы
      data.forEach(shipment => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <th>${shipment.shipmentId}</th>
          <th>${shipment.shipmentType}</th>
          <th>${shipment.weight}</th>
          <th>${shipment.volume}</th>
          <th>${shipment.orderId}</th>
          <th>${shipmentsStatus[shipment.deliveryStatus]}</th>
        `;
        shipmentTable.appendChild(row);
      });
    })
    .catch(error => {
      console.log("хуета")
    });
  });
};

// Создание груза 


console.log(shipmentsCategories["Товары для дома"]);

const cargoForm = document.querySelector('.cargoForm');

if (cargoForm) {
  cargoForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Отменяем стандартное действие отправки формы
  
    // Получаем значения из полей формы
    const cargoType = document.getElementById('cargoType').value;
    const categoryFilter = document.getElementById('categoryFilter').value;
    const cargoWeight = document.getElementById('cargoWeight').value;
    const cargoVolume = document.getElementById('cargoVolume').value;
    const orderId = document.getElementById('orderId').value;
  
    // Формируем объект с данными для отправки на сервер
    const cargoData = {
      cargoType: cargoType,
      categoryFilter: categoryFilter,
      cargoWeight: cargoWeight,
      cargoVolume: cargoVolume,
      orderId: orderId
    };

    console.log(categoryFilter);

    const queryString = `shipmentType=${cargoType}&weight=${cargoWeight}&volume=${cargoVolume}&orderId=${orderId}&deliveryStatus=0&shipmentCategory=${shipmentsCategories[categoryFilter]}`;
  
    console.log(queryString)
    // Отправляем POST-запрос на сервер
    fetch('http://localhost:8080/shipments?' + queryString, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: queryString
    })
    .then(response => {
      // Обработка ответа от сервера, если нужно
    })
    .catch(error => {
      // Обработка ошибки при выполнении запроса
    });
  });
}


// Фильтр заказов по ТС

const vehicleFilterBtn = document.querySelector(".vehicleFilterBtn");

if (vehicleFilterBtn) {
  vehicleFilterBtn.addEventListener('click', (e) => {
    e.preventDefault();
    tableBody.innerHTML = '';
    const vehicleType = document.getElementById("vehicleFilter").value;

    const queryString = `?vehicleType=${vehicleType}`;

    fetch('http://localhost:8080/ordersVehicle' + queryString)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(jsonData => {
      jsonData.forEach(order => {
        const row = document.createElement('tr');
      
        const orderId = document.createElement('th');
        orderId.textContent = order.orderId;
        orderId.classList.add("orderTd")
        row.appendChild(orderId);
      
        const orderDate = document.createElement('th');
        orderDate.textContent = order.orderDate;
        
        row.appendChild(orderDate);
      
        const status = document.createElement('th');
        status.textContent = orderStatus[order.status];
        row.appendChild(status);
      
        const deliveryDate = document.createElement('th');
        deliveryDate.textContent = order.deliveryDate;
        row.appendChild(deliveryDate);
        
        const vehicle = document.createElement('th');
        vehicle.textContent = order.vehicle;
        row.appendChild(vehicle);
  
        tableBody.appendChild(row);
      });
    })
    .catch(error => {
      // Обработка ошибок
      console.error('There has been a problem with your fetch operation:', error);
    });
   
  });
}